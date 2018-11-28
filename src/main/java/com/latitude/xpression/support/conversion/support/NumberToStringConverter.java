package com.latitude.xpression.support.conversion.support;

import com.latitude.xpression.support.conversion.impl.Converter;

/**
 * @author ERICSSON
 *
 */
public class NumberToStringConverter implements Converter<Number, CharSequence> {

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert(java. lang.Object)
     */
    @Override
    public String convert(Number source) {
        if (source != null) {
            return source.toString();
        }
        return null;
    }

}
