package com.example.cms.exception;

public class InvalidOperationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public InvalidOperationException() {
		super();
	}
	
	public InvalidOperationException(String message) {
		super(message);
	}
	
	public InvalidOperationException(String errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}
	
	public InvalidOperationException(String errorCode, String message, Throwable cause) {
		super(message,cause);
		this.errorCode=errorCode;
	}
	
	public InvalidOperationException(String message, Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
