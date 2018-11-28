package com.latitude.xpression.support.conversion;

import com.latitude.xpression.support.conversion.impl.Converter;
import com.latitude.xpression.support.conversion.impl.ConverterFactory;

/**
 * All implementations of this interface are thread-safe.
 * 
 * @author Vincenzo Autiero
 *
 */
public interface ConversionService {

    /**
     * @param sourceType
     * @param targetType
     * @param converter
     */
    public <S, T> void registerConverter(Class<?> sourceType, Class<?> targetType,
            Converter<? super S, ? extends T> converter);

    /**
     * @param sourceType
     * @param targetType
     * @param converterFactory
     */
    public void registerConverterFactory(Class<?> sourceType, Class<?> targetType,
            ConverterFactory<?, ?> converterFactory);

    /**
     * @param sourceType
     * @param targetType
     * @return
     */
    public boolean canConvert(Class<?> sourceType, Class<?> targetType);

    /**
     * @param source
     * @param targetType
     * @return
     */
    <T> T convert(Object source, Class<T> targetType);

}