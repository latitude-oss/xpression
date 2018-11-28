package com.latitude.xpression.core.el.ast;

public class TextLiteral extends LazyObject {

    public TextLiteral(String text) {
        super(text);
    }

    @Override
    public Object eval() {
        return text;
    }

}
