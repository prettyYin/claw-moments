package com.moments.claw.domain.common.utils;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class ColumnsTester {
    private static final Pattern COLUMN_PATTERN = Pattern.compile("^[a-zA-Z_]\\w*$");

    private ColumnsTester() {
    }

    public static void testColumn(String column, String message) {
        if (StringUtils.isNotEmpty(column) && !COLUMN_PATTERN.asPredicate().test(column)) {
            throw new RuntimeException(message);
        }
    }

    public static void testOrder(String order, String message) {
        if (StringUtils.isNotEmpty(order) && !"desc".equalsIgnoreCase(order) && !"asc".equalsIgnoreCase(order)) {
            throw new RuntimeException(message);
        }
    }
}
