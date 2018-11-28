/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.ast.impl;

import java.util.Optional;

import com.latitude.xpression.core.el.ast.AbstractLazyFunction;
import com.latitude.xpression.core.el.ast.BeanProperty;
import com.latitude.xpression.core.el.ast.LazyObject;
import com.latitude.xpression.core.el.ast.LazyParams;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class BeanPropertyFunction extends AbstractLazyFunction {

    public BeanPropertyFunction(String name) {
        super(name, Type.ANY, Type.ANY, -1);
    }

    @Override
    public LazyObject lazyEval(LazyParams lazyParams) {
        Optional<LazyObject> arg0Holder = lazyParams.tryGetParam(0);
        Optional<LazyObject> arg1Holder = lazyParams.tryGetParam(1);
        Preconditions.state(arg0Holder.isPresent(), "Parameter bean is required");
        Preconditions.state(arg1Holder.isPresent(), "Parameter propertyPath is required");

        LazyObject arg0 = arg0Holder.get();
        LazyObject arg1 = arg1Holder.get();
        Object target = arg0.eval();
        String propertyPath = arg1.evalAs(String.class);
        return new BeanProperty(target, propertyPath);
    }

}
