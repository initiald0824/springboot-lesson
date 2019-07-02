package com.spark.springbootlesson.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author initiald0824
 * @date 2019/7/2 14:14
 */
public class LoggerUtils {

    public static final String LOGGER_RETURN = "_logger_return";

    private static final String UNKNOWN = "unknown";

    private LoggerUtils () {
    }

    /**
     * 获取客户端ip地址
     * @param request
     * @return String
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || "".equals(ip.trim()) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || "".equals(ip.trim()) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || "".equals(ip.trim()) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        final String[] arr = ip.split(",");
        for (final String str: arr) {
            if (!UNKNOWN.equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }

    /**
     * 判断是否为ajax请求
     * @param request
     * @return String
     */
    public static String getRequestType(HttpServletRequest request) {
        return request.getHeader("X-Requested-With");
    }
}
