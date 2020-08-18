package com.example.cms.exception;
/**
 * @author
 * @date 2018/12/13
 */
public class FixGroupNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public FixGroupNotFoundException() {
		super();
	}
	
	public FixGroupNotFoundException(String message) {
		super(message);
	}
	
	public FixGroupNotFoundException(String errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}
	
	public FixGroupNotFoundException(String errorCode, String message, Throwable cause) {
		super(message,cause);
		this.errorCode=errorCode;
	}
	
	public FixGroupNotFoundException(String message, Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
