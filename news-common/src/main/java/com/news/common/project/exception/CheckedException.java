package com.news.common.project.exception;

import com.news.common.core.exception.CodeCheckedException;

public class CheckedException extends CodeCheckedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckedException() {
		super();
	}

	public CheckedException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public CheckedException(String code, String message) {
		super(code, message);
	}
	
	
	
	public CheckedException(String message) {
		super(message);
	}
}
