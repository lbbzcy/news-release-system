package com.news.common.core.exception;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApplicationException() {
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
