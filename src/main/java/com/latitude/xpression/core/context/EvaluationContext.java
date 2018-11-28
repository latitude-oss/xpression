/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.context;

import java.util.Optional;

import com.latitude.xpression.core.el.ast.AbstractLazyFunction;
import com.latitude.xpression.core.el.ast.Operator;

public interface EvaluationContext extends VariableContext {

    VariableRegistry getGlobalVariableRegistry();

    OperatorRegistry getOperatorRegistry();

    FunctionRegistry getFunctionRegistry();

    VariableContext getVariableContext();

    Optional<AbstractLazyFunction> tryResolveFunction(String functionName);

    Optional<Operator> tryResolveOperator(String operatorName);

    void clear();

}
