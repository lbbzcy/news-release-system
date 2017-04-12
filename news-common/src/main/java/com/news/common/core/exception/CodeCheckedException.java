package com.news.common.core.exception;

public class CodeCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	public CodeCheckedException() {
		super();
	}

	public CodeCheckedException(String message) {
		super(message);
	}
	
	public CodeCheckedException(String code, String message) {
		super(message);
		this.code = code;
	}

	public CodeCheckedException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
