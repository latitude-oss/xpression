package com.latitude.xpression.core.context;

import java.util.Optional;

import com.latitude.xpression.core.el.ast.Operator;

public interface OperatorRegistry {

    AddOperation register(Operator operator);

    Optional<Operator> tryResolveOperator(String key);

    boolean containsKey(Object key);

    Operator get(Object key);

    interface AddOperation {

        AddOperation withAlias(String aliasName);

    }

}