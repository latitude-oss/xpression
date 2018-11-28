package com.latitude.xpression.core.context.concurrent;

import com.latitude.xpression.core.context.VariableContext;

/**
 * @author vautiero
 *
 */
public interface VariableContextHolderStrategy {

    void clearContext();

    VariableContext getContext();

    void setContext(VariableContext variableContext);

    VariableContext createEmptyContext();

}
