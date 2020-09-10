package com.bolly.thrift.exception;

import com.niox.api2.contract.basedata.Result;
import org.apache.thrift.TException;

import java.net.SocketTimeoutException;

public class Api2Exceptions {

	public static final String RESULT_CODE_SUCCESS = "0";
	private static final String RESULT_CODE_SYS_EXCEPTION = "-1";
	private static final String RESULT_CODE_AUTHC_EXCEPTION = "-2";
	private static final String RESULT_CODE_BIZ_EXCEPTION = "1";

	private Api2Exceptions() {

	}

	public static Api2Exception transException(TException e) {
		if(e.getCause() != null && e.getCause() instanceof SocketTimeoutException) {
			return new Api2TimeoutException(e.getMessage(), e);
		}
		return new Api2Exception(e.getMessage(), e);
	}

	public static void assertResult(Result result) {
		String code = result.getCode();
		if (RESULT_CODE_SUCCESS.equals(code)) {
			return;
		}
		String msg = result.getMsg();
		if (RESULT_CODE_SYS_EXCEPTION.equals(code)) {
			throw new Api2SysException(code + msg);
		} else if (RESULT_CODE_BIZ_EXCEPTION.equals(code)) {
			throw new Api2BizException(msg);
		} else if (RESULT_CODE_AUTHC_EXCEPTION.equals(code)) {
			throw new Api2AuthcException(code + msg);
		} else {
			throw new Api2Exception(code + msg);
		}
	}

}
