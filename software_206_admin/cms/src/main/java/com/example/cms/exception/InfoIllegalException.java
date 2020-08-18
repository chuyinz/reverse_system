package com.example.cms.exception;

public class InfoIllegalException extends Exception {
        private static final long serialVersionUID = 1L;
        private String errorCode;

        public InfoIllegalException() {
            super();
        }

        public InfoIllegalException(String message) {
            super(message);
        }

        public InfoIllegalException(String errorCode, String message) {
            super(message);
            this.errorCode=errorCode;
        }

        public InfoIllegalException(String errorCode, String message, Throwable cause) {
            super(message,cause);
            this.errorCode=errorCode;
        }

        public InfoIllegalException(String message, Throwable cause) {
            super(cause);
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
