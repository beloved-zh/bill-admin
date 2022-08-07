package com.beloved.common.utils;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet工具类
 *
 * @author beloved
 */
public class ServletUtils extends ServletUtil {

    public static final String APPLICATION_JSON = "application/json";
    
    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
    
    /**
     * 返回JSON数据给客户端
     * @param response
     * @param text
     */
    public static void writeJson(HttpServletResponse response, String text) {
        write(response, text, APPLICATION_JSON_UTF8);
    }
    
    /**
     * 获取String参数
     * @param name
     * @return
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取header里面的参数
     * @param name
     * @return
     */
    public static String getHeader(String name) {
        return getRequest().getHeader(name);
    }

    
    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取 ServletRequestAttributes
     * @return
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
