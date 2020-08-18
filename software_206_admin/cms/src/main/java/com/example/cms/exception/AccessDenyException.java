package com.example.cms.exception;

public class AccessDenyException extends Exception{
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public AccessDenyException() {
        super();
    }

    public AccessDenyException(String message) {
        super(message);
    }

    public AccessDenyException(String errorCode, String message) {
        super(message);
        this.errorCode=errorCode;
}
}
