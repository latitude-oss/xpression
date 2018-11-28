package com.latitude.xpression.core.el.ast;

import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.ConversionService;

public abstract class AbstractLazyFunction {

    /**
     * Name of this function.
     */
    private String name;

    /**
     * The {@link Type} of input supported by this function
     */
    private Type inputType;

    /**
     * The {@link Type} of output produced by this function
     */
    private Type outputType;

    /**
     * Number of parameters expected for this function. <code>-1</code> denotes a variable number of parameters.
     */
    private int numParams;

    protected ConversionService conversionService;

    /**
     * Creates a new function with given name and parameter count.
     *
     * @param name The name of the function.
     * @param numParams The number of parameters for this function. <code>-1</code> denotes a variable number of
     * parameters.
     * @param booleanFunction Whether this function is a boolean function.
     */
    public AbstractLazyFunction(String name, Type inputType, Type outputType, int numParams) {
        this.name = name; // .toLowerCase(Locale.ROOT);
        this.inputType = inputType;
        this.outputType = outputType;
        this.numParams = numParams;
    }

    public String getName() {
        return name;
    }

    public int getNumParams() {
        return numParams;
    }

    public boolean numParamsVaries() {
        return numParams < 0;
    }

    public boolean isBooleanFunction() {
        return outputType == Type.BOOLEAN;
    }

    public Class<?> getExpectedInputType() {
        return inputType.getJavaType();
    }

    public boolean supportsInput(Class<?> javaType) {
        return inputType.supports(javaType);
    }

    public AbstractLazyFunction setConversionService(ConversionService conversionService) {
        Preconditions.notNull(conversionService, "ConversionService is required");
        this.conversionService = conversionService;
        return this;
    }

    public abstract LazyObject lazyEval(LazyParams lazyParams);

}
