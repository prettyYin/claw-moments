package com.moments.claw.domain.common.utils;

import cn.hutool.core.collection.CollUtil;
import java.util.Collection;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public abstract class SqlUtils {
    public static final String SQL_PATTERN = "[a-zA-Z0-9_ ,.]+";

    private SqlUtils() {
    }

    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotBlank(value) && !isValidOrderBySql(value)) {
            throw new RuntimeException("参数不符合规范，不能进行查询");
        } else {
            return value;
        }
    }

    public static boolean isValidOrderBySql(String value) {
        return value.matches("[a-zA-Z0-9_ ,.]+");
    }

    public static String in(String col, Collection<?> values, int splitSize) {
        return StrUtils.joinRound((Collection)CollUtil.split(values, splitSize).stream().map((subValues) -> {
            return in(col, subValues);
        }).collect(Collectors.toList()), " OR ", "(", ")");
    }

    public static String in1000(String col, Collection<?> values) {
        return in(col, values, 1000);
    }

    public static String in(String col, Collection<?> values) {
        return String.format("(%s IN %s)", col, StrUtils.joinRound(values, ",", "(", ")"));
    }
}
