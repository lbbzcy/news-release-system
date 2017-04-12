/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.news.common.core.exception;

public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -812611710307967457L;
	private String code;

	public BaseRuntimeException() {
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BaseRuntimeException(String code) {
		super("");
		this.code = code;
	}

	public BaseRuntimeException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}