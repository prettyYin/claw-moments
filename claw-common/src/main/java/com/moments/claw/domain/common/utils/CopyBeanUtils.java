package com.moments.claw.domain.common.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

public class CopyBeanUtils {
    private CopyBeanUtils() {
    }

    public static <T> T copyBean(Object source, Class<T> clazz) {
        T result = null;

        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return result;
    }

    public static <V, T> List<T> copyBeanList(List<V> list, Class<T> clazz) {
        return list.stream().map(t -> copyBean(t, clazz)).collect(Collectors.toList());
    }
}
