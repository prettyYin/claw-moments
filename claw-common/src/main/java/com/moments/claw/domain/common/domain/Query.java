package com.moments.claw.domain.common.domain;

import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;
import java.util.Objects;

import com.moments.claw.domain.common.utils.ColumnsTester;
import com.moments.claw.domain.common.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;

public class Query<T> {
    public Query() {
    }

    public IPage<T> getPage(Map<String, Object> params) {
        String sortField = (String)params.get("sortField");
        String sortOder = (String)params.get("sortOder");
        return StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOder) ? this.getPage(params, sortField, "asc".equalsIgnoreCase(sortOder)) : this.getPage((Map)params, (String)null, false);
    }

    public static <T> IPage<T> getPage(PageQuery pageQuery) {
        return getPage((PageQuery)pageQuery, (String)null, false);
    }

    public static <T> IPage<T> getPage(PageQuery pageQuery, String defaultOrderField, boolean isAsc) {
        long curPage = 1L;
        long limit = 10L;
        if (pageQuery != null) {
            curPage = (long)pageQuery.getPageNum();
        }

        if (pageQuery != null) {
            limit = (long)pageQuery.getPageSize();
        }

        return getPage(curPage, limit, ((PageQuery)Objects.requireNonNull(pageQuery)).getSortField(), pageQuery.getSortOrder(), defaultOrderField, isAsc);
    }

    public static <T> IPage<T> getPage(Long curPage, Long limit, String orderField, String order, String defaultOrderField, boolean isAsc) {
        Page<T> page = new Page(curPage, limit);
        ColumnsTester.testColumn(orderField, "sortField包含非法字符:" + orderField);
        if ("created_at".equalsIgnoreCase(orderField)) {
            orderField = StrUtils.humpToLine("created_at");
        } else if ("updated_at".equalsIgnoreCase(orderField)) {
            orderField = StrUtils.humpToLine("updated_at");
        }

        if (StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)) {
            ColumnsTester.testOrder(order, "order 字段仅支持asc 或者 desc");
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(orderField);
            orderItem.setAsc("asc".equalsIgnoreCase(order));
            page.addOrder(orderItem);
        }

        if (defaultOrderField != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(defaultOrderField);
            orderItem.setAsc(isAsc);
            page.addOrder(orderItem);
        }

        return page;
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        long curPage = 1L;
        long limit = 10L;
        if (params.get("pageNum") != null) {
            curPage = Long.parseLong(params.get("pageNum").toString());
        }

        if (params.get("pageSize") != null) {
            limit = Long.parseLong(params.get("pageSize").toString());
        }

        IPage<T> page = getPage(curPage, limit, (String)params.get("sortField"), (String)params.get("sortOrder"), defaultOrderField, isAsc);
        params.put("pageNum", page);
        return page;
    }
}
