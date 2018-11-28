package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class SumOperator extends Operator {

    private final MathContext mathContext;

    public SumOperator(String name, MathContext mathContext) {
        super(name, Type.NUMERIC, Type.NUMERIC, 20, true);
        Preconditions.notNull(mathContext, "MathContext is required");
        this.mathContext = mathContext;
    }

    @Override
    public Object eval(Object arg1, Object arg2) {
        Preconditions.notNull(arg1, "First operand may not be null");
        Preconditions.notNull(arg2, "Second operand may not be null");
        BigDecimal v1 = (BigDecimal) arg1;
        BigDecimal v2 = (BigDecimal) arg2;
        return v1.add(v2, mathContext);
    }

}
