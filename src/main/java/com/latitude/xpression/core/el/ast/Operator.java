package com.latitude.xpression.core.el.ast;

public abstract class Operator {

    /**
     * This operators name (pattern).
     */
    private final String name;

    /**
     * 
     */
    private final Type inputType;

    /**
     * 
     */
    private final Type outputType;

    /**
     * Operators precedence.
     */
    private final int precedence;

    /**
     * Operator is left associative.
     */
    private final boolean leftAssociative;

    public Operator(String name, Type inputType, Type outputType, int precedence, boolean leftAssociative) {
        this.name = name;
        this.inputType = inputType;
        this.outputType = outputType;
        this.precedence = precedence;
        this.leftAssociative = leftAssociative;
    }

    public String getName() {
        return name;
    }

    public Type getInputType() {
        return inputType;
    }

    public Type getOutputType() {
        return outputType;
    }

    public int getPrecedence() {
        return precedence;
    }

    public boolean isLeftAssociative() {
        return leftAssociative;
    }

    public abstract Object eval(Object leftOperand, Object rightOperand);

    public boolean isBooleanOperator() {
        return Type.BOOLEAN == outputType;
    }

    public boolean supportsInput(Type type) {
        return supportsInput(type.getJavaType());
    }

    public boolean supportsInput(Class<?> javaType) {
        return inputType.supports(javaType);
    }

}
