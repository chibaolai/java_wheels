package com.bolly.springboot.exception;

public class InValidTimeWindowException extends InValidTokenException{

    private static final long serialVersionUID = 8820043595664937772L;

    public InValidTimeWindowException() {
    }

    public InValidTimeWindowException(String message) {
        super(message);
    }

    public InValidTimeWindowException(String message, Throwable cause) {
        super(message, cause);
    }

    public InValidTimeWindowException(Throwable cause) {
        super(cause);
    }

    public InValidTimeWindowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
