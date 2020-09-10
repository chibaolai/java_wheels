package com.bolly.thrift.exception;

public class Api2TimeoutException extends Api2Exception {

	private static final long serialVersionUID = 6137056185314744807L;

	public Api2TimeoutException(String message) {
		super(message);
	}

	public Api2TimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

}
