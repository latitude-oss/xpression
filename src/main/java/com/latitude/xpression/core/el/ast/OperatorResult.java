package com.latitude.xpression.core.el.ast;

import java.math.BigDecimal;

import com.latitude.xpression.support.Preconditions;

public class OperatorResult implements LazyNumber {

    private final Operator operator;

    private final LazyNumber leftOperand;

    private final LazyNumber rightOperand;

    public OperatorResult(Operator operator, LazyNumber leftOperand, LazyNumber rightOperand) {
        Preconditions.notNull(operator, "Operator is required");
        Preconditions.notNull(leftOperand, "Left operand is required");
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public BigDecimal eval() {
        BigDecimal rightValue = (rightOperand != null) ? rightOperand.eval() : null;
        return rightValue; // operator.eval(leftOperand.eval(), rightValue);
    }

    @Override
    public String getString() {
        // TODO Auto-generated method stub
        return null;
    }

}
