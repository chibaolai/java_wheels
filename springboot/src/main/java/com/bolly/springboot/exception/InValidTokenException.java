package com.bolly.springboot.exception;

public class InValidTokenException extends RuntimeException{

    private static final long serialVersionUID = 2632383103195758111L;

    public InValidTokenException() {
    }

    public InValidTokenException(String message) {
        super(message);
    }

    public InValidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InValidTokenException(Throwable cause) {
        super(cause);
    }

    public InValidTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
