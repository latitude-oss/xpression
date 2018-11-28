package com.latitude.xpression.core.el.ast;

import java.math.BigInteger;

public class HexLiteral extends LazyObject {

    public HexLiteral(String text) {
        super(text);
    }

    @Override
    public Object eval() {
        return new BigInteger(text.substring(2), 16);
    }

}
