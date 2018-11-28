package com.latitude.xpression.core.context.concurrent;

import com.latitude.xpression.core.context.SimpleVariableContext;
import com.latitude.xpression.core.context.VariableContext;

/**
 * @author vautiero
 *
 */
public class InheritableThreadLocalVariableContextHolderStrategy implements VariableContextHolderStrategy {

    private static final ThreadLocal<VariableContext> contextHolder = new InheritableThreadLocal<VariableContext>();

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
    public void setContext(VariableContext variableContext) {
        if (variableContext == null) {
            throw new IllegalArgumentException("VariableContext is required");
        }
        contextHolder.set(variableContext);
    }

    @Override
    public VariableContext createEmptyContext() {
        return new SimpleVariableContext();
    }

}
