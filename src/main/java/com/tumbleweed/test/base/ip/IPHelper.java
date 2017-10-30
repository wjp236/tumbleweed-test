package com.tumbleweed.test.base.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述: ip获取类
 *
 * @author: mylover
 * @Time: 21/10/2017.
 */
public class IPHelper {

    public static String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null
                || ip.length() == 0
                || ip.equalsIgnoreCase("unknown")
                || ip.equalsIgnoreCase("null")) {
            ip = request.getHeader("");
        }



        return ip;
    }

}
