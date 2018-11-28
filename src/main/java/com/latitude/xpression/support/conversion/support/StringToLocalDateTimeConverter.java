package com.latitude.xpression.support.conversion.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.exception.CannotConvertValueException;
import com.latitude.xpression.support.conversion.impl.Converter;

/**
 * @author
 *
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private final DateTimeFormatter dateTimeFormatter;

    /**
     * @param pattern
     */
    public StringToLocalDateTimeConverter(String pattern) {
        Preconditions.notNull(pattern, "Pattern is required");
        dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.enel.workbeat.des.engine.core.conversion.impl.Converter#convert(java. lang.Object)
     */
    @Override
    public LocalDateTime convert(String source) {
        try {
            return LocalDateTime.parse(source, dateTimeFormatter);
        }
        catch (DateTimeParseException cause) {
            throw new CannotConvertValueException(String.format("Unrecognized date format %s", source), cause);
        }
    }

}
