package com.latitude.xpression.core.context;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import com.latitude.xpression.core.el.Constants;
import com.latitude.xpression.core.el.ast.AbstractLazyFunction;
import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.support.Preconditions;

public class StandardEvaluationContext implements EvaluationContext {

    private final FunctionRegistry functionRegistry;

    private final OperatorRegistry operatorRegistry;

    private final VariableRegistry globalVariableRegistry;

    public StandardEvaluationContext(FunctionRegistry functionRegistry, OperatorRegistry operatorRegistry,
            VariableRegistry variableRegistry) {
        Preconditions.notNull(functionRegistry, "FunctionRegistry is required");
        Preconditions.notNull(operatorRegistry, "OperatorRegistry is required");
        Preconditions.notNull(variableRegistry, "VariableRegistry is required");

        this.functionRegistry = functionRegistry;
        this.operatorRegistry = operatorRegistry;
        this.globalVariableRegistry = variableRegistry;

        // Implicit variables
        this.globalVariableRegistry.put("pi", Constants.PI);
        this.globalVariableRegistry.put("e", Constants.e);
        this.globalVariableRegistry.put("null", Constants.NULL);

    }

    @Override
    public void putVariable(String variableName, Object value) {
        getVariableContext().putVariable(variableName, value);
    }

    @Override
    public Set<Entry> entrySet() {
        return getVariableContext().entrySet();
    }

    @Override
    public Iterator<Entry> iterator() {
        return getVariableContext().iterator();
    }

    @Override
    public void clear() {
        getVariableContext().clear();
    }

    @Override
    public VariableContext getVariableContext() {
        return VariableContextHolder.getContext();
    }

    @Override
    public Optional<Object> tryResolveVariable(String variableName) {
        Optional<Object> variableHolder = globalVariableRegistry.tryFindVariable(variableName);
        if (!variableHolder.isPresent()) {
            return VariableContextHolder.getContext().tryResolveVariable(variableName);
        }
        return Optional.empty();
    }

    @Override
    public Optional<AbstractLazyFunction> tryResolveFunction(String functionName) {
        return functionRegistry.tryResolveFunction(functionName);
    }

    @Override
    public Optional<Operator> tryResolveOperator(String operatorName) {
        return operatorRegistry.tryResolveOperator(operatorName);
    }

    @Override
    public VariableRegistry getGlobalVariableRegistry() {
        return globalVariableRegistry;
    }

    @Override
    public OperatorRegistry getOperatorRegistry() {
        return operatorRegistry;
    }

    @Override
    public FunctionRegistry getFunctionRegistry() {
        return functionRegistry;
    }

    @Override
    public String toString() {
        return String.format(
                "EvaluatioContext[\n\tFunctionRegistry: %s\n\tOperatorRegistry: %s\n\tVariableContext: %s\n]",
                functionRegistry, operatorRegistry, VariableContextHolder.getContext());
    }

}
