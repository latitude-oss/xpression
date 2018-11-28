/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

import com.latitude.xpression.support.BeanWrapper;
import com.latitude.xpression.support.ReflectionUtils;

public class BeanElResolver implements ElResolver {

    private final BeanPropertyAccessStrategy fallbackStrategy;

    public BeanElResolver(BeanPropertyAccessStrategy fallbackStrategy) {
        this.fallbackStrategy = fallbackStrategy;
    }

    @Override
    public Object resolveValue(ElContext context, Object target, Object property) {
        if (target != null && property != null) {
            BeanWrapper beanWrapper = BeanWrapper.target(target, fallbackStrategy);
            String propertyName = property.toString();
            Class<?> propertyType = ReflectionUtils.resolveFieldType(target.getClass(), propertyName);
            try {
                Object value = beanWrapper.getPropertyValue(propertyType, propertyName);
                context.setPropertyResolved(true);
                return value;
            }
            catch (BeanPropertyAccessException exception) {
                throw exception;
            }
        }
        return null;
    }

}
