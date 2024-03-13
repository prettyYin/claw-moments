package com.moments.claw.domain.common.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.hutool.core.convert.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class ServletUtils {
    private static final String UNKONWN = "unknown";

    private ServletUtils() {
    }

    public static String getParameter(String name) {
        return ((HttpServletRequest)Objects.requireNonNull(getRequest())).getParameter(name);
    }

    public static String getHeard(String name) {
        return getRequest().getHeader(name);
    }

    public static boolean headerContainsKey(String name) {
        Enumeration headerNames = getRequest().getHeaderNames();

        String key;
        do {
            if (!headerNames.hasMoreElements()) {
                return false;
            }

            key = (String)headerNames.nextElement();
        } while(!name.equals(key));

        return true;
    }

    public static Long getLongValueHeader(String name) {
        return Long.valueOf(getRequest().getHeader(name));
    }

    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = getRequestAttributes();
        return Objects.nonNull(requestAttributes) ? requestAttributes.getRequest() : null;
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes)attributes;
    }

    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().print(string);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public static String getRequestIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String getCurrentRequestIpAddress() {
        HttpServletRequest request = getRequest();
        return getRequestIpAddress((HttpServletRequest)Objects.requireNonNull(request));
    }
}
