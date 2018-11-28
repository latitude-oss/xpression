package com.latitude.xpression.core.el.ast;

public class Variable extends LazyObject {

    private final Object value;

    public Variable(String text, Object value) {
        super(text);
        this.value = value;
    }

    @Override
    public Object eval() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

}
