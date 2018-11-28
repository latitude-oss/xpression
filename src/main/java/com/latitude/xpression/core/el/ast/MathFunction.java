package com.latitude.xpression.core.el.ast;

import java.math.MathContext;

import com.latitude.xpression.support.Preconditions;

public abstract class MathFunction extends AbstractFunction {

    protected final MathContext mathContext;

    public MathFunction(String name, int numParams, MathContext mathContext) {
        super(name, Type.NUMERIC, Type.NUMERIC, numParams);
        Preconditions.notNull(mathContext, "MathContext is required");
        this.mathContext = mathContext;
    }

}
