package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;

import com.latitude.xpression.core.el.ast.AbstractLazyFunction;
import com.latitude.xpression.core.el.ast.LazyObject;
import com.latitude.xpression.core.el.ast.LazyParams;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class IfFunction extends AbstractLazyFunction {

    public IfFunction(String name) {
        super(name, Type.ANY, Type.ANY, 3);
    }

    @Override
    public LazyObject lazyEval(LazyParams lazyParams) {
        LazyObject parameter = lazyParams.get(0);
        Preconditions.notNull(parameter, "At least one parameter is required");
        Object result = parameter.eval();
        boolean isTrue = !result.equals(BigDecimal.ZERO) || result.equals(Boolean.TRUE);
        return isTrue ? lazyParams.get(1) : lazyParams.get(2);
    }

}
