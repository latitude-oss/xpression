package com.latitude.xpression.core.el.bean;

public class BeanPropertyAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BeanPropertyAccessException() {
    }

    public BeanPropertyAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanPropertyAccessException(String message) {
        super(message);
    }

    public BeanPropertyAccessException(Throwable cause) {
        super(cause);
    }

}
