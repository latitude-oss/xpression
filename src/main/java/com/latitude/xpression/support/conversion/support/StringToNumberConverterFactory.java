package com.latitude.xpression.support.conversion.support;

import com.latitude.xpression.support.NumberUtils;
import com.latitude.xpression.support.StringUtils;
import com.latitude.xpression.support.conversion.impl.Converter;
import com.latitude.xpression.support.conversion.impl.ConverterFactory;

/**
 * @author ERICSSON
 *
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.impl.ConverterFactory# getConverter(java.lang.Class)
     */
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<T>(targetType);
    }

    /**
     * @author ERICSSON
     *
     * @param <T>
     */
    public static class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert( java.lang.Object)
         */
        @Override
        public T convert(String source) {
            if (!StringUtils.hasText(source)) {
                return null;
            }
            return NumberUtils.parseNumber(source, targetType);
        }

    }

}
