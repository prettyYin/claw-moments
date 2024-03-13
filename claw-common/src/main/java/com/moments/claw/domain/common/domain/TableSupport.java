package com.moments.claw.domain.common.domain;

import com.moments.claw.domain.common.utils.ColumnsTester;
import com.moments.claw.domain.common.utils.ServletUtils;

public class TableSupport {
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    public static final String IS_ASC = "isAsc";

    private TableSupport() {
    }

    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt("pageNum", 1));
        pageDomain.setPageSize(ServletUtils.getParameterToInt("pageSize", 10));
        pageDomain.setOrderByColumn(ServletUtils.getParameter("orderByColumn"));
        pageDomain.setIsAsc(ServletUtils.getParameter("isAsc"));
        ColumnsTester.testOrder(ServletUtils.getParameter("sortOrder"), "sortOrder参数包含非法字符");
        ColumnsTester.testColumn(pageDomain.getOrderByColumn(), "排序字段包含非法字符:" + pageDomain.getOrderByColumn());
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
