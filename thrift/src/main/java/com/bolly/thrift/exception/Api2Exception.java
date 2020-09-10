package com.bolly.thrift.exception;

public class Api2Exception extends RuntimeException {

    private static final long serialVersionUID = 267134992353238254L;

    public Api2Exception(String message) {
        super(message);
    }

    public Api2Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
