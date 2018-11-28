package com.latitude.xpression.core.el.ast;

import java.math.BigDecimal;

public class Literal extends LazyObject {

    public Literal(String text) {
        super(text);
    }

    @Override
    public Object eval() {
        if (text.equalsIgnoreCase("NULL")) {
            return null;
        }
        return BigDecimal.valueOf(Double.valueOf(text));
    }

}
