package com.latitude.xpression.support.conversion.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.latitude.xpression.support.conversion.support.BooleanToStringConverter;
import com.latitude.xpression.support.conversion.support.NumberToNumberConverterFactory;
import com.latitude.xpression.support.conversion.support.NumberToStringConverter;
import com.latitude.xpression.support.conversion.support.StringToBooleanConverter;
import com.latitude.xpression.support.conversion.support.StringToDateConverter;
import com.latitude.xpression.support.conversion.support.StringToLocalDateConverter;
import com.latitude.xpression.support.conversion.support.StringToLocalDateTimeConverter;
import com.latitude.xpression.support.conversion.support.StringToNumberConverterFactory;

public class DefaultConversionConfiguration implements ConversionConfiguration {

    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void configureConverters(ConverterRegistry registry) {

        registry.registerConverter(Number.class, String.class, new NumberToStringConverter());
        registry.registerConverter(String.class, Boolean.class, new StringToBooleanConverter());
        registry.registerConverter(Boolean.class, String.class, new BooleanToStringConverter());
        registry.registerConverter(String.class, Date.class, new StringToDateConverter(DATE_PATTERN));
        registry.registerConverter(String.class, LocalDate.class, new StringToLocalDateConverter(DATE_PATTERN));
        registry.registerConverter(String.class, LocalDateTime.class, new StringToLocalDateTimeConverter(DATE_PATTERN));

        registry.registerConverterFactory(Number.class, Number.class, new NumberToNumberConverterFactory());
        registry.registerConverterFactory(String.class, Number.class, new StringToNumberConverterFactory());
    }

}
