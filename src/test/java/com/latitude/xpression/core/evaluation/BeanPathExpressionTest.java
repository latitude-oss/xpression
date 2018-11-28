/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.latitude.xpression.core.Bar;
import com.latitude.xpression.core.Foo;
import com.latitude.xpression.core.context.SimpleVariableContext;
import com.latitude.xpression.core.context.VariableContext;
import com.latitude.xpression.core.el.ast.BeanPathExpression;
import com.latitude.xpression.core.el.bean.ArrayElResolver;
import com.latitude.xpression.core.el.bean.BeanElResolver;
import com.latitude.xpression.core.el.bean.CompositeElResolver;
import com.latitude.xpression.core.el.bean.FieldAccessStrategy;
import com.latitude.xpression.core.el.bean.ListElResolver;
import com.latitude.xpression.core.el.bean.MapElResolver;

public class BeanPathExpressionTest {

    private CompositeElResolver elResolver;

    private VariableContext variableContext;

    @Before
    public void setup() {
        elResolver = new CompositeElResolver();
        elResolver.add(new ArrayElResolver());
        elResolver.add(new ListElResolver());
        elResolver.add(new MapElResolver());
        elResolver.add(new BeanElResolver(new FieldAccessStrategy()));
        variableContext = new SimpleVariableContext();
    }

    @Test
    public void shouldParseVariableExpression() {
        Bar expected = new Bar("bar01");
        variableContext.putVariable("bar", expected);
        BeanPathExpression expression = new BeanPathExpression("bar", variableContext, elResolver);
        Bar actual = expression.evalAs(Bar.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldParseBeanPathExpression() {
        variableContext.putVariable("bar", new Bar("bar01"));
        BeanPathExpression expression = new BeanPathExpression("bar.value", variableContext, elResolver);
        String actual = expression.evalAs(String.class);
        Assert.assertEquals("bar01", actual);
    }

    @Test
    public void shouldParseListExpression01() {
        Collection<Bar> bars = new ArrayList<Bar>();
        bars.add(new Bar("bar01"));
        variableContext.putVariable("bars", bars);
        BeanPathExpression expression = new BeanPathExpression("bars(0).value", variableContext, elResolver);
        String actual = expression.evalAs(String.class);
        Assert.assertEquals("bar01", actual);
    }

    @Test
    public void shouldParseMapExpression01() {
        Map<String, Bar> bars = new HashMap<String, Bar>();
        bars.put("key1", new Bar("bar01"));
        variableContext.putVariable("bars", bars);
        BeanPathExpression expression = new BeanPathExpression("bars['key1'].value", variableContext, elResolver);
        String actual = expression.evalAs(String.class);
        Assert.assertEquals("bar01", actual);
    }

    @Test
    public void shouldParseArrayExpression01() {
        Bar[] bars = new Bar[1];
        bars[0] = new Bar("bar01");
        variableContext.putVariable("bars", bars);
        BeanPathExpression expression = new BeanPathExpression("bars[0].value", variableContext, elResolver);
        String actual = expression.evalAs(String.class);
        Assert.assertEquals("bar01", actual);
    }

    @Test
    public void shouldParseNestedExpression01() {
        Collection<Foo> foos = new ArrayList<Foo>();
        Bar expected = new Bar("bar01");
        foos.add(new Foo(expected));
        variableContext.putVariable("foos", foos);
        BeanPathExpression expression = new BeanPathExpression("foos(0).bar", variableContext, elResolver);
        Bar actual = expression.evalAs(Bar.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldParseNestedExpression02() {
        Collection<Foo> foos = new ArrayList<Foo>();
        foos.add(new Foo(new Bar("bar01")));
        variableContext.putVariable("foos", foos);
        BeanPathExpression expression = new BeanPathExpression("foos(0).bar.value", variableContext, elResolver);
        String actual = expression.evalAs(String.class);
        Assert.assertEquals("bar01", actual);
    }

    @Test
    public void shouldParseNestedExpression03() {
        Collection<Foo> foos = new ArrayList<Foo>();
        foos.add(new Foo(new Bar("bar01", new Bar("childBar"))));
        variableContext.putVariable("foos", foos);
        BeanPathExpression expression = new BeanPathExpression("foos(0).bar.child.value", variableContext, elResolver);
        String actual = expression.evalAs(String.class);
        Assert.assertEquals("childBar", actual);
    }

}
