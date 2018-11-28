package com.latitude.xpression.core.el.ast.impl;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;

public class NotEqualsOperator extends Operator {

    public NotEqualsOperator(String name) {
        super(name, Type.ANY, Type.BOOLEAN, 7, false);
    }

    @Override
    public Object eval(Object v1, Object v2) {
        if (v1 == v2) {
            return Boolean.FALSE;
        }
        if (v1 == null || v2 == null) {
            return Boolean.TRUE;
        }
        return v1.equals(v2) ? Boolean.FALSE : Boolean.TRUE;
    }

}
