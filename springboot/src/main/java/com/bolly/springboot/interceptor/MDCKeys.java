package com.bolly.springboot.interceptor;

public class MDCKeys {

    /**
     * 客户端地址
     */
    public static final String REMOTE_ADDR = "REMOTE_ADDR";

    /**
     * 请求的URI
     */
    public static final String REQUEST_URI = "REQUEST_URI";

    /**
     * 用户id
     */
    public static final String USER_ID = "USER_ID";

    /**
     * 用户名
     */
    public static final String USER_NAME = "USER_NAME";
    
    /**
     * 跟踪ID
     */
    public static final String TRACE_ID = "TRACE_ID";

    private MDCKeys() {

    }

}
