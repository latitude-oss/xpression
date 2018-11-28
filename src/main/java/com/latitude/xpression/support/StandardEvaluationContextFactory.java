/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.support;

import com.latitude.xpression.core.context.EvaluationContext;
import com.latitude.xpression.core.context.FunctionRegistry;
import com.latitude.xpression.core.context.OperatorRegistry;
import com.latitude.xpression.core.context.StandardEvaluationContext;
import com.latitude.xpression.core.context.VariableRegistryTreeMapImpl;

public class StandardEvaluationContextFactory implements EvaluationContextFactory {

    private final FunctionRegistry functionRegistry;

    private final OperatorRegistry operatorRegistry;

    public StandardEvaluationContextFactory(FunctionRegistry functionRegistry, OperatorRegistry operatorRegistry) {
        Preconditions.notNull(functionRegistry, "FunctionRegistry is required");
        Preconditions.notNull(operatorRegistry, "OperatorRegistry is required");
        this.functionRegistry = functionRegistry;
        this.operatorRegistry = operatorRegistry;
    }

    @Override
    public EvaluationContext create() {
        return new StandardEvaluationContext(functionRegistry, operatorRegistry, new VariableRegistryTreeMapImpl());
    }

}
