package com.bolly.thrift.exception;

public class Api2UnsupportException extends Api2Exception{

	private static final long serialVersionUID = 1632966289585459376L;

	public Api2UnsupportException(String message) {
        super(message);
    }

    public Api2UnsupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
