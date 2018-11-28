package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class SubtractionOperator extends Operator {

    private final MathContext mathContext;

    public SubtractionOperator(String name, MathContext mathContext) {
        super(name, Type.NUMERIC, Type.NUMERIC, 20, true);
        Preconditions.notNull(mathContext, "MathContext is required");
        this.mathContext = mathContext;
    }

    @Override
    public Object eval(Object arg1, Object arg2) {
        Preconditions.notNull(arg1, "Left operator is required");
        Preconditions.notNull(arg2, "Right operator is required");
        BigDecimal v1 = (BigDecimal) arg1;
        BigDecimal v2 = (BigDecimal) arg2;
        return v1.subtract(v2, this.mathContext);
    }

}
