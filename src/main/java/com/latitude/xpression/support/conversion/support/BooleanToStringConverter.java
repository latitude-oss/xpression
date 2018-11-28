/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.support.conversion.support;

import com.latitude.xpression.support.conversion.impl.Converter;

public class BooleanToStringConverter implements Converter<Boolean, String> {

    @Override
    public String convert(Boolean source) {
        return Boolean.toString(source);
    }

}
