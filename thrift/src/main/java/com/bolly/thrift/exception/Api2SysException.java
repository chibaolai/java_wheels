package com.bolly.thrift.exception;

public class Api2SysException extends Api2Exception {

    private static final long serialVersionUID = -8180591451997934479L;

    public Api2SysException(String message) {
        super(message);
    }

    public Api2SysException(String message, Throwable cause) {
        super(message, cause);
    }
}
