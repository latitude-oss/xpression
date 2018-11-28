package com.latitude.xpression.support.conversion.exception;

public class PrecisionLossException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PrecisionLossException() {
    }

    public PrecisionLossException(String message) {
        super(message);
    }

    public PrecisionLossException(Throwable cause) {
        super(cause);
    }

}
