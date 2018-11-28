package com.latitude.xpression.core.el.ast.impl;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class OrOperator extends Operator {

    public OrOperator(String name) {
        super(name, Type.BOOLEAN, Type.BOOLEAN, 2, false);
    }

    @Override
    public Object eval(Object v1, Object v2) {
        Preconditions.notNull(v1, "Left operand is required!");
        Preconditions.notNull(v2, "Right operand is required!");
        boolean b1 = !v1.equals(Boolean.FALSE);
        boolean b2 = !v2.equals(Boolean.FALSE);
        return b1 || b2 ? Boolean.TRUE : Boolean.FALSE;
    }

}
