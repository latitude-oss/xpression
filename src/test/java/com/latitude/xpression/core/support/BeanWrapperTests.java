/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.support;

import org.junit.Assert;
import org.junit.Test;

import com.latitude.xpression.core.Bar;
import com.latitude.xpression.core.ExpressionTestSupport;
import com.latitude.xpression.core.Foo;
import com.latitude.xpression.core.el.bean.FieldAccessStrategy;
import com.latitude.xpression.support.BeanWrapper;

public class BeanWrapperTests extends ExpressionTestSupport {

    @Test
    public void shouldNavigate2ndLevelPath() {
        Foo target = new Foo(new Bar("bar01"));
        BeanWrapper beanWrapper = BeanWrapper.target(target);
        beanWrapper.setFallbackStrategy(new FieldAccessStrategy());
        String actual = beanWrapper.evaluatePath("bar.value");
        Assert.assertEquals("bar01", actual);
    }

    @Test
    public void shouldNavigate3ndLevelPath() {
        Foo target = new Foo(new Bar("bar01", new Bar("childBar01")));
        BeanWrapper beanWrapper = BeanWrapper.target(target);
        beanWrapper.setFallbackStrategy(new FieldAccessStrategy());
        String actual = beanWrapper.evaluatePath("bar.child.value");
        Assert.assertEquals("childBar01", actual);
    }

}
