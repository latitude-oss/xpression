package com.latitude.xpression.support.conversion.exception;

public class CannotConvertValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CannotConvertValueException() {
    }

    public CannotConvertValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotConvertValueException(String message) {
        super(message);
    }

    public CannotConvertValueException(Throwable cause) {
        super(cause);
    }

}
