package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

import com.latitude.xpression.core.ExpressionException;
import com.latitude.xpression.core.el.ast.MathFunction;
import com.latitude.xpression.support.Preconditions;

public class SqrtFunction extends MathFunction {

    public SqrtFunction(String name, MathContext mathContext) {
        super(name, 1, mathContext);
    }

    public Object doEvaluation(List<Object> parameters) {
        Preconditions.notNull(parameters.get(0), "A parameter is required");
        /*
         * From The Java Programmers Guide To numerical Computing (Ronald Mak, 2003)
         */

        Object parameter = parameters.get(0);
        BigDecimal x = (BigDecimal) parameter;
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            return new BigDecimal(0);
        }
        if (x.signum() < 0) {
            throw new ExpressionException("Argument to SQRT() function must not be negative");
        }
        BigInteger n = x.movePointRight(mathContext.getPrecision() << 1).toBigInteger();

        int bits = (n.bitLength() + 1) >> 1;
        BigInteger ix = n.shiftRight(bits);
        BigInteger ixPrev;

        do {
            ixPrev = ix;
            ix = ix.add(n.divide(ix)).shiftRight(1);
            // Give other threads a chance to work;
            Thread.yield();
        } while (ix.compareTo(ixPrev) != 0);

        return new BigDecimal(ix, mathContext.getPrecision());
    }

}
