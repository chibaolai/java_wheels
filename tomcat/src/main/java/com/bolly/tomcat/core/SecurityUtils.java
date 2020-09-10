package com.bolly.tomcat.core;

import java.util.UUID;

public class SecurityUtils {

    private SecurityUtils() {

    }

    public static Subject getSubject() {
        return SecurityContext.getSubject();
    }

    /**
     * 获取当前的账号ID
     * @return
     */
    public static Object currentAccountId() {
        Subject subject = getSubject();
        if (subject != null) {
            return subject.getPrincipal();
        }
        return null;
    }
    
    /**
     * 获取客户端ip地址
     * @return
     */
    public static String getRemoteAddr() {
    	return SecurityContext.getRemoteAddr();
    }

    /**
     * 获取客户端类型
     * @return
     */
    public static Integer getAppType() {
        return SecurityContext.getAppType();
    }
    
    /**
     * 获取访问随机数
     * @return
     */
    public static String getNonce() {
        return (SecurityContext.getNonce() != null ? SecurityContext.getNonce() : UUID.randomUUID().toString());
    }

    /**
     * 客户端种别
     * @return
     */
    public static Integer getAppKind() {
        return SecurityContext.getAppKind();
    }

    /**
     * 获取时间戳
     * @return
     */
    public static Long getTimeStamp() {
        return SecurityContext.getTimeStamp();
    }


    /**
     * 获取APP版本
     * @return
     */
    public static String getAppVersion() {
        return SecurityContext.getAppversion();
    }
}
