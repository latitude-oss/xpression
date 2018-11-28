/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.ast;

import java.math.BigDecimal;

public enum Type {

    ANY(Object.class), BOOLEAN(Boolean.class), NUMERIC(BigDecimal.class), TEXT(String.class);

    private Type(Class<?> type) {
        this.type = type;
    }

    private final Class<?> type;

    public Class<?> getJavaType() {
        return this.type;
    }

    public boolean supports(Class<?> javaType) {
        return type.isAssignableFrom(javaType);
    }

}
