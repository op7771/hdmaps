package com.blueigm.hdmap.uaa.Exceptions;

public class TokenInvalidException extends ApiServiceException {

    private static final long serialVersionUID = -6034204038217876179L;

    public TokenInvalidException() {
        super("Token create invalid error.");
    }

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenInvalidException(Throwable cause) {
        super("Token create invalid error.", cause);
    }

    public TokenInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
