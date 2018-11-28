package com.latitude.xpression.support.conversion.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.exception.CannotConvertValueException;
import com.latitude.xpression.support.conversion.impl.Converter;

/**
 * @author
 *
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter;

    /**
     * @param pattern
     */
    public StringToLocalDateConverter(String pattern) {
        Preconditions.notNull(pattern, "Pattern is required");
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert(java. lang.Object)
     */
    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, formatter);
        }
        catch (DateTimeParseException cause) {
            throw new CannotConvertValueException(String.format("Unrecognized date format %s", source), cause);
        }
    }

}
