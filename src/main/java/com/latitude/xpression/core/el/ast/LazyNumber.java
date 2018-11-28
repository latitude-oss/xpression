package com.latitude.xpression.core.el.ast;

import java.math.BigDecimal;

/**
 * LazyNumber interface created for lazily evaluated functions
 */
public interface LazyNumber {

    public BigDecimal eval();

    public String getString();

}
