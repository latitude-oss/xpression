package com.latitude.xpression.core.el.ast;

public abstract class LazyObject {

    protected final String text;

    public LazyObject() {
        this(null);
    }

    public LazyObject(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public <T> T evalAs(Class<T> type) {
        Object result = eval();
        if (result == null) {
            return null;
        }
        if (type.isAssignableFrom(result.getClass())) {
            return type.cast(result);
        }
        throw new ClassCastException(String.format("Cannot cast from %s to %s", result.getClass(), type));
    }

    public abstract Object eval();

}
