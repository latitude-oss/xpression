package com.latitude.xpression.core.el.ast;

public class OperationResult extends LazyObject {

    private final Operator operator;

    private final Object leftOperand;

    private final Object rightOperand;

    public OperationResult(Operator operator, Object leftOperand, Object rightOperand) {
        super();
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Object eval() {
        return operator.eval(leftOperand, rightOperand);
    }

}
