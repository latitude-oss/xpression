package com.latitude.xpression.support.conversion.impl;

/**
 * @author
 *
 */
public class ConverterAdapter {

    private final Converter<Object, Object> converter;

    private final ConvertiblePair typeInfo;

    @SuppressWarnings("unchecked")
    public ConverterAdapter(Converter<?, ?> converter, Class<?> sourceType, Class<?> targetType) {
        this.converter = (Converter<Object, Object>) converter;
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
     * @param source
     * @param sourceType
     * @param targetType
     * @return
     */
    public Object convert(Object source) {
        if (source == null) {
            return null;
        }
        return converter.convert(source);
    }

}
