package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;
import java.util.List;

import com.latitude.xpression.core.el.ast.AbstractFunction;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class NotFunction extends AbstractFunction {

    public NotFunction(String name) {
        super(name, Type.BOOLEAN, Type.BOOLEAN, 1);
    }

    @Override
    public Object doEvaluation(List<Object> parameters) {
        Object parameter = parameters.get(0);
        Preconditions.notNull(parameter, "Parameter at index 0 is required");
        boolean zero = BigDecimal.ZERO.equals(parameter) || Boolean.FALSE.equals(parameter);
        return zero ? Boolean.TRUE : Boolean.FALSE;
    }

}
