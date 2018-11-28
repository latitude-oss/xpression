package com.latitude.xpression.support.conversion;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.latitude.xpression.support.conversion.exception.CannotConvertValueException;
import com.latitude.xpression.support.conversion.impl.Converter;
import com.latitude.xpression.support.conversion.impl.ConverterAdapter;
import com.latitude.xpression.support.conversion.impl.ConverterFactory;
import com.latitude.xpression.support.conversion.impl.ConverterRegistry;
import com.latitude.xpression.support.conversion.impl.ConvertiblePair;

/**
 * This class is responsible to do the main conversion between types.
 * 
 * This class is thread-safe.
 * 
 * @author
 *
 */
public class DefaultConversionService implements ConversionService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ConverterRegistry registry;

    public DefaultConversionService() {
        this(null);
    }

    public DefaultConversionService(ConverterRegistry registry) {
        if (registry == null) {
            this.registry = new ConverterRegistry();
        }
        else {
            this.registry = registry;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.ConversionService# registerConverter(java.lang.Class,
     * java.lang.Class, com.enel.workbeat.des.engine.core.conversion.impl.Converter)
     */
    @Override
    public <S, T> void registerConverter(Class<?> sourceType, Class<?> targetType,
            Converter<? super S, ? extends T> converter) {
        registry.registerConverter(sourceType, targetType, converter);
        if (logger.isDebugEnabled()) {
            logger.debug("Registered converter {}, from {} to {}", converter.getClass().getSimpleName(),
                    sourceType.getName(), targetType.getName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.ConversionService# registerConverterFactory(java.lang.Class,
     * java.lang.Class, com.enel.workbeat.des.engine.core.conversion.impl.ConverterFactory)
     */
    @Override
    public void registerConverterFactory(Class<?> sourceType, Class<?> targetType,
            ConverterFactory<?, ?> converterFactory) {
        registry.registerConverterFactory(sourceType, targetType, converterFactory);
        if (logger.isDebugEnabled()) {
            logger.debug("Registered ConverterFactory {}, from {} to {}", converterFactory.getClass().getSimpleName(),
                    sourceType.getName(), targetType.getName());
        }
    }

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        ConvertiblePair key = new ConvertiblePair(sourceType, targetType);
        Optional<ConverterAdapter> candidate = registry.tryResolveConverter(key);
        return candidate.isPresent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.ConversionService#convert( java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        ConvertiblePair key = new ConvertiblePair(source.getClass(), targetType);
        Optional<ConverterAdapter> candidate = registry.tryResolveConverter(key);
        if (candidate.isPresent()) {
            ConverterAdapter converter = candidate.get();
            synchronized (this) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Converting value {} from {} to {}", source, source.getClass().getName(),
                            targetType.getName());
                }
                Object result = converter.convert(source);
                if (targetType.isAssignableFrom(result.getClass())) {
                    return targetType.cast(result);
                }
            }
        }
        CannotConvertValueException exception = new CannotConvertValueException(
                String.format("Unable to convert value %s from %s to %s", source, source.getClass(), targetType));
        if (logger.isErrorEnabled()) {
            logger.error("Unable to convert value {} from {} to {}", source, source.getClass().getName(),
                    targetType.getName(), exception);
        }
        throw exception;
    }

}
