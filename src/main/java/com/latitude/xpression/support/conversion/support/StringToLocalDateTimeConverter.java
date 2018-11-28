/*
 * Copyright 2018 Latitude Srls
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.latitude.xpression.support.conversion.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.exception.CannotConvertValueException;
import com.latitude.xpression.support.conversion.impl.Converter;

/**
 * @author Vincenzo Autiero
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
