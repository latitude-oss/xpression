/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.support.bean.el;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.latitude.xpression.core.el.bean.BeanExpressionToken;
import com.latitude.xpression.core.el.bean.BeanExpressionTokenizer;

public class BeanExpressionTokenizerTests {

    @Before
    public void setup() {

    }

    @Test
    public void shouldParseArrayExpression() {
        BeanExpressionTokenizer tokenizer = new BeanExpressionTokenizer("foos[1]");
        BeanExpressionToken token1 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.VARIABLE, token1.getTokenType());
        Assert.assertEquals("foos", token1.getValue());

        BeanExpressionToken token2 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.ARRAY_ACCESS, token2.getTokenType());
        Assert.assertEquals("1", token2.getValue());
        Assert.assertFalse(tokenizer.hasNext());
    }

    @Test
    public void shouldParseMapExpression() {
        BeanExpressionTokenizer tokenizer = new BeanExpressionTokenizer("foos['key1']");
        BeanExpressionToken token1 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.VARIABLE, token1.getTokenType());
        Assert.assertEquals("foos", token1.getValue());

        BeanExpressionToken token2 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.MAP_ACCESS, token2.getTokenType());
        Assert.assertEquals("key1", token2.getValue());
        Assert.assertFalse(tokenizer.hasNext());
    }

    @Test
    public void shouldParseNestedMapExpression() {
        BeanExpressionTokenizer tokenizer = new BeanExpressionTokenizer("foos['key1']['key2']");
        BeanExpressionToken token1 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.VARIABLE, token1.getTokenType());
        Assert.assertEquals("foos", token1.getValue());

        BeanExpressionToken token2 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.MAP_ACCESS, token2.getTokenType());
        Assert.assertEquals("key1", token2.getValue());

        BeanExpressionToken token3 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.MAP_ACCESS, token3.getTokenType());
        Assert.assertEquals("key2", token3.getValue());
        Assert.assertFalse(tokenizer.hasNext());
    }

    @Test
    public void shouldParseListExpression() {
        BeanExpressionTokenizer tokenizer = new BeanExpressionTokenizer("foos(2)");
        BeanExpressionToken token1 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.VARIABLE, token1.getTokenType());
        Assert.assertEquals("foos", token1.getValue());

        BeanExpressionToken token2 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.LIST_ACCESS, token2.getTokenType());
        Assert.assertEquals("2", token2.getValue());
        Assert.assertFalse(tokenizer.hasNext());
    }

    @Test
    public void shouldParseBeanExpression() {
        BeanExpressionTokenizer tokenizer = new BeanExpressionTokenizer("foo.bar");
        BeanExpressionToken token1 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.VARIABLE, token1.getTokenType());
        Assert.assertEquals("foo", token1.getValue());

        BeanExpressionToken token2 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.BEAN_PROPERTY_ACCESS, token2.getTokenType());
        Assert.assertEquals("bar", token2.getValue());
        Assert.assertFalse(tokenizer.hasNext());
    }

    @Test
    public void shouldParseNestedExpressions() {

        BeanExpressionTokenizer tokenizer = new BeanExpressionTokenizer("foos(1).bar.values['value01']");
        BeanExpressionToken token1 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.VARIABLE, token1.getTokenType());
        Assert.assertEquals("foos", token1.getValue());

        BeanExpressionToken token2 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.LIST_ACCESS, token2.getTokenType());
        Assert.assertEquals("1", token2.getValue());

        BeanExpressionToken token3 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.BEAN_PROPERTY_ACCESS, token3.getTokenType());
        Assert.assertEquals("bar", token3.getValue());

        BeanExpressionToken token4 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.BEAN_PROPERTY_ACCESS, token4.getTokenType());
        Assert.assertEquals("values", token4.getValue());

        BeanExpressionToken token5 = tokenizer.next();
        Assert.assertEquals(BeanExpressionToken.TokenType.MAP_ACCESS, token5.getTokenType());
        Assert.assertEquals("value01", token5.getValue());

        Assert.assertFalse(tokenizer.hasNext());
    }

}
