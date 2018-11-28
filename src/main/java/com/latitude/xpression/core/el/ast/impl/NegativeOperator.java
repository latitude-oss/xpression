package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;

import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.core.el.ast.UnaryOperator;

public class NegativeOperator extends UnaryOperator {

    public NegativeOperator(String name) {
        super(name, Type.NUMERIC, Type.NUMERIC, 60, false);
    }

    @Override
    public Object evalUnary(Object v1) {
        BigDecimal value = (BigDecimal) v1;
        return value.multiply(BigDecimal.valueOf(1));
    }

}
