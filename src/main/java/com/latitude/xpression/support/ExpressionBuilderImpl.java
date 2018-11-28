/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.support;

import com.latitude.xpression.Expression;
import com.latitude.xpression.core.ExpressionImpl;
import com.latitude.xpression.core.el.bean.ElResolver;
import com.latitude.xpression.support.conversion.ConversionService;

public class ExpressionBuilderImpl implements ExpressionBuilder {

    private final ConversionService conversionService;

    private final ElResolver elResolver;

    public ExpressionBuilderImpl(ElResolver elResolver, ConversionService conversionService) {
        this.elResolver = elResolver;
        this.conversionService = conversionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.latitude.governor.expression.impl.ExpressionBuilder#build(java.lang.String)
     */
    @Override
    public Expression build(String expression) {
        return new ExpressionImpl(expression, elResolver, conversionService);
    }

}
