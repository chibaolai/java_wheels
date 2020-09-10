package com.bolly.thrift.exception;

public class Api2BizException extends Api2Exception {

    private static final long serialVersionUID = -5149364303022937840L;

    public Api2BizException(String message) {
        super(message);
    }

    public Api2BizException(String message, Throwable cause) {
        super(message, cause);
    }

}
