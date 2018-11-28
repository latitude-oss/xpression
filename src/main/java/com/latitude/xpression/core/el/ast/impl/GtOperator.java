package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class GtOperator extends Operator {

    public GtOperator(String name) {
        super(name, Type.NUMERIC, Type.BOOLEAN, 10, false);
    }

    @Override
    public Object eval(Object arg1, Object arg2) {
        Preconditions.notNull(arg1, "Left operand is required!");
        Preconditions.notNull(arg2, "Right operand is required!");
        BigDecimal val1 = (BigDecimal) arg1;
        BigDecimal val2 = (BigDecimal) arg2;
        return val1.compareTo(val2) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

}
