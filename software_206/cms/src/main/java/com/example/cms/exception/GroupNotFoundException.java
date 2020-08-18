package com.example.cms.exception;
/**
 * @author
 * @date 2018/12/13
 */
public class GroupNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public GroupNotFoundException() {
		super();
	}
	
	public GroupNotFoundException(String message) {
		super(message);
	}
	
	public GroupNotFoundException(String errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}
	
	public GroupNotFoundException(String errorCode, String message, Throwable cause) {
		super(message,cause);
		this.errorCode=errorCode;
	}
	
	public GroupNotFoundException(String message, Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
