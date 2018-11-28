package com.latitude.xpression.core.context;

import com.latitude.xpression.core.context.concurrent.InheritableThreadLocalVariableContextHolderStrategy;
import com.latitude.xpression.core.context.concurrent.VariableContextHolderStrategy;

/**
 * @author vautiero
 *
 */
public class VariableContextHolder {

    private static VariableContextHolderStrategy strategy;

    private VariableContextHolder() {
    }

    static {
        initialize();
    }

    private static void initialize() {
        strategy = new InheritableThreadLocalVariableContextHolderStrategy();
    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static VariableContext getContext() {
        return strategy.getContext();
    }

    public static void setContext(VariableContext variableContext) {
        strategy.setContext(variableContext);
    }

    public static VariableContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

}
