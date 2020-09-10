package com.bolly.thrift.exception;

public class Api2ConnectException extends Api2Exception {

    private static final long serialVersionUID = -1417828058774038983L;

    public Api2ConnectException(String message) {
        super(message);
    }

    public Api2ConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
