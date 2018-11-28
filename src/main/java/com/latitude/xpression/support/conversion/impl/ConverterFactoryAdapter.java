package com.latitude.xpression.support.conversion.impl;

/**
 * @author
 *
 */
public class ConverterFactoryAdapter {

    private final ConverterFactory<Object, Object> converterFactory;

    private final ConvertiblePair typeInfo;

    /**
     * @param converterFactory
     * @param sourceType
     * @param targetType
     */
    @SuppressWarnings("unchecked")
    public ConverterFactoryAdapter(ConverterFactory<?, ?> converterFactory, Class<?> sourceType, Class<?> targetType) {
        this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        typeInfo = new ConvertiblePair(sourceType, targetType);
    }

    /**
     * @param sourceType
     * @param targetType
     * @return
     */
    public boolean supports(Class<?> sourceType, Class<?> targetType) {
        return typeInfo.isAssignableFrom(sourceType, targetType);
    }

    /**
     * @return
     */
    public ConverterFactory<Object, Object> getConverterFactory() {
        return converterFactory;
    }

}
