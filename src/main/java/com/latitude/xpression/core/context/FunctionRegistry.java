package com.latitude.xpression.core.context;

import java.util.Optional;

import com.latitude.xpression.core.el.ast.AbstractLazyFunction;

public interface FunctionRegistry {

    AddFunction register(AbstractLazyFunction function);

    Optional<AbstractLazyFunction> tryResolveFunction(String key);

    boolean containsKey(Object key);

    AbstractLazyFunction get(Object key);

    interface AddFunction {

        AddFunction withAlias(String aliasName);

    }

}