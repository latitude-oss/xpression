package com.latitude.xpression.core;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.latitude.xpression.core.el.ShuntingYardFunctionImpl;
import com.latitude.xpression.core.el.Token;
import com.latitude.xpression.core.el.TokenType;

public class ShuntingYardFunctionImplTest extends ExpressionTestSupport {

    private ShuntingYardFunctionImpl subject;

    @Before
    public void init() {
        subject = new ShuntingYardFunctionImpl(getOperatorRegistry(), getFunctionRegistry());
    }

    @Test
    public void shouldApplyShuntingYard() {
        List<Token> result = subject.apply("1==1");
        Iterator<Token> it = result.iterator();
        Token t1 = it.next();
        Assert.assertNotNull(t1);
        Assert.assertTrue(t1.sameTypeOf(TokenType.LITERAL));
        Assert.assertEquals("1", t1.getSurface());
        Token t2 = it.next();
        Assert.assertTrue(t2.sameTypeOf(TokenType.LITERAL));
        Assert.assertEquals("1", t2.getSurface());
        Token t3 = it.next();
        Assert.assertTrue(t3.sameTypeOf(TokenType.OPERATOR));
        Assert.assertEquals("==", t3.getSurface());
    }

    @Test
    public void shouldApplyShuntingYardOnComplexExpression01() {
        List<Token> result = subject.apply("beanProperty(foo, 'bar') eq 'value'");
        Iterator<Token> it = result.iterator();
        Assert.assertTrue(it.next().sameTypeOf(TokenType.OPEN_BRAKET));
        Assert.assertTrue(it.next().sameTypeOf(TokenType.VARIABLE));
        Assert.assertTrue(it.next().sameTypeOf(TokenType.STRINGPARAM));
        Assert.assertTrue(it.next().sameTypeOf(TokenType.FUNCTION));
        Assert.assertTrue(it.next().sameTypeOf(TokenType.STRINGPARAM));
        Assert.assertTrue(it.next().sameTypeOf(TokenType.OPERATOR));

    }

}
