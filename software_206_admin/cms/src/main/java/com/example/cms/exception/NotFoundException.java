package com.example.cms.exception;

public class NotFoundException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String errorCode;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode=errorCode;
    }

    public NotFoundException(String errorCode, String message, Throwable cause) {
        super(message,cause);
        this.errorCode=errorCode;
    }

    public NotFoundException(String message, Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
