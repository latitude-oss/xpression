package com.latitude.xpression.support.conversion.support;

import com.latitude.xpression.support.NumberUtils;
import com.latitude.xpression.support.conversion.exception.PrecisionLossException;
import com.latitude.xpression.support.conversion.impl.Converter;
import com.latitude.xpression.support.conversion.impl.ConverterFactory;

/**
 * @author
 *
 */
public class NumberToNumberConverterFactory implements ConverterFactory<Number, Number> {

    @Override
    public <T extends Number> Converter<Number, T> getConverter(Class<T> targetType) {
        return new NumberToNumber<T>(targetType);
    }

    /**
     * @author ERICSSON
     *
     * @param <T>
     */
    public static class NumberToNumber<T extends Number> implements Converter<Number, T> {

        private final Class<T> targetType;

        /**
         * @param targetType
         */
        public NumberToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert( java.lang.Object)
         */
        @Override
        public T convert(Number source) {
            try {
                return NumberUtils.convertNumberToTargetClass(source, targetType);
            }
            catch (IllegalArgumentException cause) {
                throw new PrecisionLossException(cause);
            }
        }

    }

}
