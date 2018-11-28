/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import com.latitude.xpression.core.el.ast.MathFunction;
import com.latitude.xpression.support.Preconditions;

public class RoundFunction extends MathFunction {

    public RoundFunction(String name, MathContext mathContext) {
        super(name, -1, mathContext);
    }

    @Override
    public Object doEvaluation(List<Object> parameters) {
        Preconditions.notNull(parameters.get(0), "First argument may not be null");
        BigDecimal toRound = (BigDecimal) parameters.get(0);
        Integer precisionAsInteger = 1;
        if (parameters.size() > 1) {
            BigDecimal precision = (BigDecimal) parameters.get(1);
            precisionAsInteger = precision != null ? precision.intValue() : 1;
        }
        toRound.setScale(precisionAsInteger, mathContext.getRoundingMode());
        return toRound.round(mathContext);
    }

}
