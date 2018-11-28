package com.latitude.xpression.support.conversion.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents the main repository for registered {@link Converters} and {@link ConverterFactory} able to do
 * the conversion process.
 * 
 * This class is thread-safe.
 * 
 * @author
 *
 */
public class ConverterRegistry {

    private final Map<ConvertiblePair, ConverterAdapter> converters;

    private final Map<ConvertiblePair, ConverterFactoryAdapter> converterFactories;

    public ConverterRegistry() {
        converters = new ConcurrentHashMap<ConvertiblePair, ConverterAdapter>();
        converterFactories = new ConcurrentHashMap<ConvertiblePair, ConverterFactoryAdapter>();
    }

    /**
     * @param sourceType
     * @param targetType
     * @param converter
     */
    public void registerConverter(Class<?> sourceType, Class<?> targetType, Converter<?, ?> converter) {
        ConvertiblePair key = new ConvertiblePair(sourceType, targetType);
        if (!converters.containsKey(key)) {
            converters.put(key, new ConverterAdapter(converter, sourceType, targetType));
        }
    }

    /**
     * @param sourceType
     * @param targetType
     * @param converter
     */
    public void registerConverterFactory(Class<?> sourceType, Class<?> targetType,
            ConverterFactory<?, ?> converterFactory) {
        ConvertiblePair key = new ConvertiblePair(sourceType, targetType);
        if (!converterFactories.containsKey(key)) {
            converterFactories.put(key, new ConverterFactoryAdapter(converterFactory, sourceType, targetType));
        }
    }

    /**
     * @param key
     * @return
     */
    public Optional<ConverterAdapter> tryResolveConverter(ConvertiblePair key) {
        synchronized (this) {
            if (converters.containsKey(key)) {
                return Optional.ofNullable(converters.get(key));
            }
            else {
                for (ConverterAdapter converterAdapter : converters.values()) {
                    if (converterAdapter.supports(key.getSourceType(), key.getTargetType())) {
                        return Optional.of(converterAdapter);
                    }
                }
                for (ConverterFactoryAdapter converterFactoryAdapter : converterFactories.values()) {
                    if (converterFactoryAdapter.supports(key.getSourceType(), key.getTargetType())) {
                        ConverterFactory<Object, Object> converterFactory = converterFactoryAdapter
                                .getConverterFactory();
                        Converter<?, ?> converter = converterFactory.getConverter(key.getTargetType());
                        ConverterAdapter converterAdapter = new ConverterAdapter(converter, key.getSourceType(),
                                key.getTargetType());
                        converters.put(key, converterAdapter);
                        return Optional.of(converterAdapter);
                    }
                }
                return Optional.empty();
            }
        }
    }

}