package com.latitude.xpression.core.el.ast;

import com.latitude.xpression.support.Preconditions;

public abstract class UnaryOperator extends Operator {

    public UnaryOperator(String name, Type inputType, Type outputType, int precedence, boolean leftAssociative) {
        super(name, inputType, outputType, precedence, leftAssociative);
    }

    @Override
    public Object eval(Object v1, Object v2) {
        Preconditions.notNull(v1, "Left operator is required");
        Preconditions.state(v2 == null, "Did not expect a second parameter for unary operator");
        return evalUnary(v1);
    }

    abstract public Object evalUnary(Object v1);

}
