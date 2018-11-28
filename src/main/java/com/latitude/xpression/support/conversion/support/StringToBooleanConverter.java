package com.latitude.xpression.support.conversion.support;

import java.util.HashSet;
import java.util.Set;

import com.latitude.xpression.support.StringUtils;
import com.latitude.xpression.support.conversion.exception.CannotConvertValueException;
import com.latitude.xpression.support.conversion.impl.Converter;

/**
 * @author
 *
 */
public class StringToBooleanConverter implements Converter<String, Boolean> {

    private static final Set<String> trueValues = new HashSet<String>(4);

    private static final Set<String> falseValues = new HashSet<String>(4);

    static {
        trueValues.add("true");
        trueValues.add("on");
        trueValues.add("1");
        trueValues.add("yes");
        falseValues.add("false");
        falseValues.add("off");
        falseValues.add("0");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert(java. lang.Object)
     */
    @Override
    public Boolean convert(String source) {
        if (!StringUtils.hasText(source)) {
            return null;
        }
        String sourceLower = source.toLowerCase();
        if (trueValues.contains(sourceLower)) {
            return Boolean.TRUE;
        }
        else if (falseValues.contains(sourceLower)) {
            return Boolean.FALSE;
        }
        else {
            throw new CannotConvertValueException("Invalid boolean value '" + sourceLower + "'");
        }
    }

}
