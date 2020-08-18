package com.example.cms.exception;

/**
 * @author
 * @date 2018/12/13
 */
public class ClassesNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public ClassesNotFoundException() {
		super();
	}
	
	public ClassesNotFoundException(String message) {
		super(message);
	}
	
	public ClassesNotFoundException(String errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}
	
	public ClassesNotFoundException(String errorCode, String message, Throwable cause) {
		super(message,cause);
		this.errorCode=errorCode;
	}
	
	public ClassesNotFoundException(String message, Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}

