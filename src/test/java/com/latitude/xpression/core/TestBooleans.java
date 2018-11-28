package com.latitude.xpression.core;

import org.junit.Assert;
import org.junit.Test;

import com.latitude.xpression.Expression;

public class TestBooleans extends ExpressionTestSupport {

    @Test
    public void testIsBoolean() {
        Expression e = newExpression("1==1");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("a==b");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("(1==1)||(c==a+b)");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("(z+z==x-y)||(c==a+b)");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("NOT(a+b)");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("a+b");
        Assert.assertFalse(e.isBoolean(newEvaluationContext()));

        e = newExpression("(a==b)+(b==c)");
        Assert.assertFalse(e.isBoolean(newEvaluationContext()));

        e = newExpression("SQRT(2)");
        Assert.assertFalse(e.isBoolean(newEvaluationContext()));

        e = newExpression("SQRT(2) == SQRT(b)");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("IF(a==b,x+y,x-y)");
        Assert.assertFalse(e.isBoolean(newEvaluationContext()));

        e = newExpression("IF(a==b,x==y,a==b)");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));

        e = newExpression("jsonPath(payload, 'foo') eq 'value'");
        Assert.assertTrue(e.isBoolean(newEvaluationContext()));
    }

}
