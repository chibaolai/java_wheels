package com.bolly.tomcat.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 安全上下文
 * @author Erich
 *
 */
public class SecurityContext {

    private static final ThreadLocal<Map<Object, Object>> RESOURCES = new InheritableThreadLocal<>();

    private static final String SUBJECT_KEY = SecurityContext.class.getName() + "_SUBJECT_KEY";
    
    private static final String REMOTE_ADDR = SecurityContext.class.getName() + "_REMOTE_ADDR";

    private static final String APP_TYPE_KEY = SecurityContext.class.getName() + "_APP_TYPE_KEY";
    
    private static final String NONCE_KEY = SecurityContext.class.getName() + "_NONCE_KEY";

    private static final String APP_KIND_KEY = SecurityContext.class.getName() + "_APP_KIND_KEY";

    private static final String TIMESTAMP_KEY = SecurityContext.class.getName() + "_TIMESTAMP_KEY";
    
    private static final String APP_VERSION = SecurityContext.class.getName() + "_APP_VERSION";

    private SecurityContext() {
    }

    public static void bindSubject(Subject subject) {
        if (subject != null) {
            set(SUBJECT_KEY, subject);
        }
    }
    
    public static void bindRemoteAddr(String remoteAddr) {
    	if(remoteAddr != null) {
    		set(REMOTE_ADDR, remoteAddr);
    	}
    }

    public static void bindAppType(Integer appType) {
        if (appType != null) {
            set(APP_TYPE_KEY, appType);
        }
    }
    
    public static void bindNonce(String nonce) {
        if (nonce != null) {
            set(NONCE_KEY, nonce);
        }
    }

    public static void bindAppKind(Integer appKind) {
        if (appKind != null) {
            set(APP_KIND_KEY, appKind);
        }
    }

    public static void bindTimeStamp(Long timeStamp) {
        if (timeStamp != null) {
            set(TIMESTAMP_KEY, timeStamp);
        }
    }

    public static void bindAppVersion(String appVersion) {
        if (appVersion != null) {
            set(APP_VERSION, appVersion);
        }
    }
    
    public static Subject getSubject() {
        return (Subject) get(SUBJECT_KEY);
    }
    
    public static String getRemoteAddr() {
    	return (String) get(REMOTE_ADDR);
    }

    public static Integer getAppType() {
        return (Integer) get(APP_TYPE_KEY);
    }
    
    public static String getNonce() {
        return (String) get(NONCE_KEY);
    }

    public static Integer getAppKind() {
        return (Integer) get(APP_KIND_KEY);
    }

    public static Long getTimeStamp() {
        return (Long) get(TIMESTAMP_KEY);
    }
    
    public static String getAppversion() {
        return (String) get(APP_VERSION);
    }
    
    public static Object get(Object key) {
        return getResources().get(key);
    }
    
	public static void set(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            getResources().remove(key);
            return;
        }
        getResources().put(key, value);
    }

    
    public static void clear() {
        RESOURCES.remove();
    }
    
    private static Map<Object, Object> getResources() {
        Map<Object, Object> resMap = RESOURCES.get();
        if (resMap == null) {
        	resMap = new HashMap<Object, Object>();
        	RESOURCES.set(resMap);
        }
        return resMap;
    }
}
