package com.bolly.thrift.exception;

public class Api2AuthcException extends Api2Exception {

	private static final long serialVersionUID = 3553941319464350338L;

	public Api2AuthcException(String message) {
		super(message);
	}

	public Api2AuthcException(String message, Throwable cause) {
		super(message, cause);
	}
}
