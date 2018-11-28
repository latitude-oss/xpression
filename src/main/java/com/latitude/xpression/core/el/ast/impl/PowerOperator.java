package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class PowerOperator extends Operator {

    private final MathContext mathContext;

    public PowerOperator(String name, MathContext mathContext) {
        super(name, Type.NUMERIC, Type.NUMERIC, 40, false);
        Preconditions.notNull(mathContext, "MathContext is required");
        this.mathContext = mathContext;
    }

    @Override
    public Object eval(Object arg1, Object arg2) {
        Preconditions.notNull(arg1, "Left operator is required");
        Preconditions.notNull(arg2, "Right operator is required");
        BigDecimal v1 = (BigDecimal) arg1;
        BigDecimal v2 = (BigDecimal) arg2;

        /*- 
         * Thanks to Gene Marin:
         * http://stackoverflow.com/questions/3579779/how-to-do-a-fractional-power-on-bigdecimal-in-java
         */
        int signOf2 = v2.signum();
        double dn1 = v1.doubleValue();
        v2 = v2.multiply(new BigDecimal(signOf2)); // n2 is now positive
        BigDecimal remainderOf2 = v2.remainder(BigDecimal.ONE);
        BigDecimal n2IntPart = v2.subtract(remainderOf2);
        BigDecimal intPow = v1.pow(n2IntPart.intValueExact(), mathContext);
        BigDecimal doublePow = new BigDecimal(Math.pow(dn1, remainderOf2.doubleValue()));

        BigDecimal result = intPow.multiply(doublePow, mathContext);
        if (signOf2 == -1) {
            result = BigDecimal.ONE.divide(result, mathContext.getPrecision(), RoundingMode.HALF_UP);
        }
        return result;
    }

}
