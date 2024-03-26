package com.moments.claw.domain.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author claw-moments
 *  2022/8/30 9:45
 * description: PageHelper分页工具类
 */

public class PageHelperUtils {
    /**
     * pagehelper手动分页
     * @param currentPage 当前页
     * @param <T>
     */
    public static <T> PageInfo<T> getPageInfo(int currentPage, int pageSize, List<T> list) {
        int total = list.size();
        if (total > pageSize) {
            int toIndex = pageSize * currentPage;
            if (toIndex > total) {
                toIndex = total;
            }
            if (pageSize * (currentPage - 1) < toIndex) {
                list = list.subList(pageSize * (currentPage - 1), toIndex);
            }
            else {
                list = new ArrayList<>();
            }
        }
        Page<T> page = new Page<>(currentPage, pageSize);
        page.addAll(list);
        page.setPages((total + pageSize - 1) / pageSize);
        page.setTotal(total);

        PageInfo<T> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }
}

