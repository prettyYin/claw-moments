package com.moments.claw.domain.common.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.domain.Query;
import com.moments.claw.domain.common.response.TableDataInfo;
import org.springframework.beans.BeanUtils;

public class PaginationUtil {
    private PaginationUtil() {
    }

    public static <V, D> TableDataInfo<V> from(IPage<D> page, Function<? super D, ? extends V> map) {
        TableDataInfo<V> tableDataInfo = new TableDataInfo();
        tableDataInfo.setRows((List)page.getRecords().stream().map(map).collect(Collectors.toList()));
        tableDataInfo.setTotal(page.getTotal());
        tableDataInfo.setPageSize((int)page.getSize());
        tableDataInfo.setCurrPage((int)page.getCurrent());
        tableDataInfo.setPageNum((int)page.getPages());
        return tableDataInfo;
    }

    public static <V, D> TableDataInfo<V> from(IPage<D> page, Class<V> voClass) {
        return from(page, (data) -> {
            try {
                V vo = voClass.getConstructor().newInstance();
                BeanUtils.copyProperties(data, vo);
                return vo;
            } catch (Exception var3) {
                return null;
            }
        });
    }

    public static <V, D> TableDataInfo<V> from(IPage<D> page, Class<V> voClass, Function<D, V> beanTransfer) {
        return from(page, (data) -> {
            try {
                return beanTransfer.apply(data);
            } catch (Exception var3) {
                return null;
            }
        });
    }

    public static <V, T, S extends IService<T>, Q extends PageQuery> TableDataInfo<V> paged(S service, Q pagedParam, Wrapper<T> lqw, Class<V> itemType) {
        IPage<T> page = service.page(Query.getPage(pagedParam), lqw);
        return from(page, itemType);
    }

    public static <V, T, S extends IService<T>, Q extends PageQuery> TableDataInfo<V> paged(S service, Q pagedParam, Wrapper<T> lqw, Class<V> itemType, Function<T, V> beanTransfer) {
        IPage<T> page = service.page(Query.getPage(pagedParam), lqw);
        return from(page, itemType, beanTransfer);
    }

    public static <V, T, S extends IService<T>, Q extends PageQuery> TableDataInfo<V> paged(S service, Q pagedParam, Wrapper<T> lqw, Function<? super T, ? extends V> map) {
        IPage<T> page = service.page(Query.getPage(pagedParam), lqw);
        return from(page, map);
    }

    /**
     * 手动分页
     * @param list 被分页集合
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return 分页列表
     */
    public static <V> TableDataInfo<V> handPaged(List<V> list,Integer pageSize,Integer pageNum) {
        if (list.size() == 0) {
            return new TableDataInfo<>();
        }
        List<V> collect = list.stream().skip((long) pageSize * (pageNum - 1)).limit(pageSize).collect(Collectors.toList());
        TableDataInfo<V> result = new TableDataInfo<>();
        result.setPageSize(pageSize);
        result.setCurrPage(pageNum);
        result.setPageNum((list.size() + pageSize - 1) / pageSize);
        result.setTotal(list.size());
        result.setRows(collect);
        return result;
    }
}
