package com.latitude.xpression.support.conversion.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.exception.CannotConvertValueException;
import com.latitude.xpression.support.conversion.impl.Converter;

/**
 * @author
 *
 */
public class StringToDateConverter implements Converter<String, Date> {

    private final DateFormat dateFormat;

    /**
     * @param pattern
     */
    public StringToDateConverter(String pattern) {
        Preconditions.notNull(pattern, "Pattern is required");
        dateFormat = new SimpleDateFormat(pattern);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert(java. lang.Object)
     */
    @Override
    public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        }
        catch (ParseException cause) {
            throw new CannotConvertValueException(String.format("Unrecognized date format %s", source), cause);
        }
    }

}
