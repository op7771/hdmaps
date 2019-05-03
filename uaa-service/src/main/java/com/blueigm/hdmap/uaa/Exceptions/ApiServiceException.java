package com.blueigm.hdmap.uaa.Exceptions;

public class ApiServiceException extends Exception {

    public ApiServiceException() {
    }

    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiServiceException(Throwable cause) {
        super(cause);
    }

    public ApiServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
