package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;

import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.core.el.ast.UnaryOperator;
import com.latitude.xpression.support.Preconditions;

public class PositiveOperator extends UnaryOperator {

    public PositiveOperator(String name) {
        super(name, Type.NUMERIC, Type.NUMERIC, 60, false);
    }

    @Override
    public Object evalUnary(Object v1) {
        Preconditions.notNull(v1, "Left operator is required");
        BigDecimal value = (BigDecimal) v1;
        return value.multiply(BigDecimal.ONE);
    }

}
