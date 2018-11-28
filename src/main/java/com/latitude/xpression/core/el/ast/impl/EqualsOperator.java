package com.latitude.xpression.core.el.ast.impl;

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;

public class EqualsOperator extends Operator {

    public EqualsOperator(String name) {
        super(name, Type.ANY, Type.BOOLEAN, 7, false);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object eval(Object arg1, Object arg2) {
        if (arg1 == arg2) {
            return true;
        }
        if (arg1 == null || arg2 == null) {
            return false;
        }

        if (areComparable(arg1, arg2)) {
            return ((Comparable) arg1).compareTo((Comparable) arg2) == 0;
        }
        return arg1.equals(arg2);

    }

    private boolean areComparable(Object arg1, Object arg2) {
        return Comparable.class.isAssignableFrom(arg1.getClass()) && Comparable.class.isAssignableFrom(arg2.getClass());
    }

}
