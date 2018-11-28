/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core;

import java.math.MathContext;

import org.junit.BeforeClass;

import com.latitude.xpression.Expression;
import com.latitude.xpression.FunctionRegistryConfiguration;
import com.latitude.xpression.OperatorRegistryConfiguration;
import com.latitude.xpression.core.context.EvaluationContext;
import com.latitude.xpression.core.context.FunctionRegistry;
import com.latitude.xpression.core.context.FunctionRegistryTreeMapImpl;
import com.latitude.xpression.core.context.OperatorRegistry;
import com.latitude.xpression.core.context.OperatorRegistryTreeMapImpl;
import com.latitude.xpression.core.context.StandardEvaluationContext;
import com.latitude.xpression.core.context.VariableRegistryTreeMapImpl;
import com.latitude.xpression.core.el.Tokenizer;
import com.latitude.xpression.core.el.bean.ArrayElResolver;
import com.latitude.xpression.core.el.bean.BeanElResolver;
import com.latitude.xpression.core.el.bean.CompositeElResolver;
import com.latitude.xpression.core.el.bean.FieldAccessStrategy;
import com.latitude.xpression.core.el.bean.ListElResolver;
import com.latitude.xpression.core.el.bean.MapElResolver;
import com.latitude.xpression.support.conversion.ConversionService;
import com.latitude.xpression.support.conversion.DefaultConversionService;
import com.latitude.xpression.support.conversion.impl.ConversionConfiguration;
import com.latitude.xpression.support.conversion.impl.ConverterRegistry;
import com.latitude.xpression.support.conversion.impl.DefaultConversionConfiguration;

public abstract class ExpressionTestSupport {

    private static OperatorRegistryTreeMapImpl operatorRegistry;

    private static FunctionRegistryTreeMapImpl functionRegistry;

    private static ConversionService conversionService;

    protected OperatorRegistry getOperatorRegistry() {
        return operatorRegistry;
    }

    protected FunctionRegistry getFunctionRegistry() {
        return functionRegistry;
    }

    protected Expression newExpression(String expression) {
        CompositeElResolver elResolver = new CompositeElResolver();
        elResolver.add(new ArrayElResolver());
        elResolver.add(new ListElResolver());
        elResolver.add(new MapElResolver());
        elResolver.add(new BeanElResolver(new FieldAccessStrategy()));
        return new ExpressionImpl(expression, elResolver, conversionService);
    }

    protected EvaluationContext newEvaluationContext() {
        return new StandardEvaluationContext(functionRegistry, operatorRegistry, new VariableRegistryTreeMapImpl());
    }

    protected Tokenizer newTokenizer(String expression) {
        return new Tokenizer(expression, operatorRegistry, functionRegistry);
    }

    @BeforeClass
    public static void setupTestCase() {
        ConverterRegistry converterRegistry = new ConverterRegistry();
        ConversionConfiguration conversionConfiguration = new DefaultConversionConfiguration();
        conversionConfiguration.configureConverters(converterRegistry);
        conversionService = new DefaultConversionService(converterRegistry);
        operatorRegistry = new OperatorRegistryTreeMapImpl();
        functionRegistry = new FunctionRegistryTreeMapImpl();

        OperatorRegistryConfiguration opConfig = new DefaultOperatorRegistryConfiguration(MathContext.DECIMAL32);
        opConfig.configure(operatorRegistry);

        FunctionRegistryConfiguration funConfig = new DefaultFunctionRegistryConfiguration(conversionService,
                MathContext.DECIMAL32);
        funConfig.configure(functionRegistry);
    }

}
