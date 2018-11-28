package com.latitude.xpression.support.conversion.impl;

/**
 * @author
 *
 * @param <S>
 * @param <T>
 */
public interface ConverterFactory<S, T> {

    /**
     * @param targetType
     * @return
     */
    public <K extends T> Converter<S, K> getConverter(Class<K> targetType);

}
