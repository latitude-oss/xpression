/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.ast;

import com.latitude.xpression.core.el.bean.BeanPropertyAccessStrategy;
import com.latitude.xpression.core.el.bean.FieldAccessStrategy;
import com.latitude.xpression.support.BeanWrapper;

public class BeanProperty extends LazyObject {

    private final Object target;

    private final String propertyPath;

    private final BeanPropertyAccessStrategy fallbackStrategy;

    public BeanProperty(Object target, String propertyPath) {
        this.target = target;
        this.propertyPath = propertyPath;
        this.fallbackStrategy = new FieldAccessStrategy();
    }

    @Override
    public Object eval() {
        return BeanWrapper.target(target, fallbackStrategy).evaluatePath(propertyPath);
    }

}
