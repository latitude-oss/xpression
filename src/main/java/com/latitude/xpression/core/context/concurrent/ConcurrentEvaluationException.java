package com.latitude.xpression.core.context.concurrent;

/**
 * @author Vincenzo Autiero
 *
 */
public class ConcurrentEvaluationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConcurrentEvaluationException() {
    }

    public ConcurrentEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConcurrentEvaluationException(String message) {
        super(message);
    }

    public ConcurrentEvaluationException(Throwable cause) {
        super(cause);
    }

}
