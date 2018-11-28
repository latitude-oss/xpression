package com.latitude.xpression;

import java.math.MathContext;

import com.latitude.xpression.core.DefaultFunctionRegistryConfiguration;
import com.latitude.xpression.core.DefaultOperatorRegistryConfiguration;
import com.latitude.xpression.core.ExpressionImpl;
import com.latitude.xpression.core.context.EvaluationContext;
import com.latitude.xpression.core.context.FunctionRegistry;
import com.latitude.xpression.core.context.FunctionRegistryTreeMapImpl;
import com.latitude.xpression.core.context.OperatorRegistry;
import com.latitude.xpression.core.context.OperatorRegistryTreeMapImpl;
import com.latitude.xpression.core.context.StandardEvaluationContext;
import com.latitude.xpression.core.context.VariableRegistryTreeMapImpl;
import com.latitude.xpression.core.el.bean.ArrayElResolver;
import com.latitude.xpression.core.el.bean.BeanElResolver;
import com.latitude.xpression.core.el.bean.CompositeElResolver;
import com.latitude.xpression.core.el.bean.FieldAccessStrategy;
import com.latitude.xpression.core.el.bean.ListElResolver;
import com.latitude.xpression.core.el.bean.MapElResolver;
import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.ConversionService;
import com.latitude.xpression.support.conversion.DefaultConversionService;
import com.latitude.xpression.support.conversion.impl.ConversionConfiguration;
import com.latitude.xpression.support.conversion.impl.ConverterRegistry;
import com.latitude.xpression.support.conversion.impl.DefaultConversionConfiguration;

/**
 * @author Vincenzo Autiero
 *
 */
public class ExpressionParser {

    private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL32;

    private final EvaluationContext evaluationContext;

    private final CompositeElResolver elResolver;

    private final ConversionService conversionService;

    /**
     * 
     */
    public ExpressionParser() {
        this(null, null, new DefaultConversionConfiguration());
    }

    /**
     * @param functionRegistryConfiguration
     * @param operatorRegistryConfiguration
     * @param conversionConfiguration
     */
    public ExpressionParser(FunctionRegistryConfiguration functionRegistryConfiguration,
            OperatorRegistryConfiguration operatorRegistryConfiguration,
            ConversionConfiguration conversionConfiguration) {

        Preconditions.notNull(conversionConfiguration, "ConversionConfiguration is required");

        ConverterRegistry converterRegistry = new ConverterRegistry();
        conversionConfiguration.configureConverters(converterRegistry);
        conversionService = new DefaultConversionService(converterRegistry);

        if (functionRegistryConfiguration == null) {
            functionRegistryConfiguration = new DefaultFunctionRegistryConfiguration(conversionService,
                    DEFAULT_MATH_CONTEXT);
        }
        FunctionRegistry functionRegistry = functionRegistryConfiguration.configure(new FunctionRegistryTreeMapImpl());

        if (operatorRegistryConfiguration == null) {
            operatorRegistryConfiguration = new DefaultOperatorRegistryConfiguration(DEFAULT_MATH_CONTEXT);
        }
        OperatorRegistry operatorRegistry = operatorRegistryConfiguration.configure(new OperatorRegistryTreeMapImpl());

        evaluationContext = new StandardEvaluationContext(functionRegistry, operatorRegistry,
                new VariableRegistryTreeMapImpl());
        elResolver = new CompositeElResolver();
        elResolver.add(new ArrayElResolver());
        elResolver.add(new ListElResolver());
        elResolver.add(new MapElResolver());
        elResolver.add(new BeanElResolver(new FieldAccessStrategy()));
    }

    /**
     * @param name
     * @param value
     */
    public void putVariable(String name, Object value) {
        evaluationContext.putVariable(name, value);
    }

    /**
     * @param expression
     * @return
     */
    public boolean isBooleanExpression(String expression) {
        Expression exp = buildExpression(expression);
        return exp.isBoolean(evaluationContext);
    }

    /**
     * @param expression
     * @return
     */
    public String evaluateAsText(String expression) {
        return evaluateAs(expression, String.class);
    }

    /**
     * @param expression
     * @param type
     * @return
     */
    public <T> T evaluateAs(String expression, Class<T> type) {
        try {
            Expression exp = buildExpression(expression);
            return exp.evaluateAs(type, evaluationContext);
        }
        finally {
            evaluationContext.clear();
        }
    }

    private Expression buildExpression(String expression) {
        Preconditions.hasText(expression, "expression cannot be null or empty");
        return new ExpressionImpl(expression, elResolver, conversionService);

    }

}
