package com.example.dream_house.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject:dream_house
 * @BelongsPackage:com.example.dream_house.service
 * @Author:Uestc_Xiye
 * @CreateTime:2023-12-18 09:12:22
 */
public class getIP {

    /**
     * @param request
     * @param response
     * @return
     */
    public String get_IP(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 设置响应头允许ajax跨域访问，星号表示所有的异域请求都可以接受
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return getIpAddr(request);
    }

    /**
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        // 获取请求头"x-forwarded-for"对应的value
        String ip = request.getHeader("x-forwarded-for");
        // 如果获取的IP值为空
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果以上方式获取的IP值都为空
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            // 则直接获取IP地址
            ip = request.getRemoteAddr();
        }
        // 返回IP地址
        return ip;
    }
}
