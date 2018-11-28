package com.latitude.xpression.core.context.concurrent;

import com.latitude.xpression.core.context.SimpleVariableContext;
import com.latitude.xpression.core.context.VariableContext;

/**
 * @author vautiero
 *
 */
public class ThreadLocalVariableContextHolderStrategy implements VariableContextHolderStrategy {

    private static final ThreadLocal<VariableContext> contextHolder = new ThreadLocal<VariableContext>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public VariableContext getContext() {
        VariableContext context = contextHolder.get();
        if (context == null) {
            context = createEmptyContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void setContext(VariableContext logContext) {
        if (logContext == null) {
            throw new IllegalArgumentException("LogContext is required");
        }
        contextHolder.set(logContext);
    }

    @Override
    public VariableContext createEmptyContext() {
        return new SimpleVariableContext();
    }

}
