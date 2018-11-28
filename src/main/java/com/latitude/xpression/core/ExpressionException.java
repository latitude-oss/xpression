package com.latitude.xpression.core;

public class ExpressionException extends RuntimeException {

    private static final long serialVersionUID = 8409966136882646257L;

    public ExpressionException() {
        super();
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(Throwable cause) {
        super(cause);
    }

}
