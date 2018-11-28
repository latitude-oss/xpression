/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

import java.lang.reflect.Array;

public class ArrayElResolver implements ElResolver {

    public Object resolveValue(ElContext context, Object target, Object property) {
        if (target != null && target.getClass().isArray()) {
            int index = toInteger(property);
            if (index >= 0 && index < Array.getLength(target)) {
                context.setPropertyResolved(true);
                return Array.get(target, index);
            }
        }
        return null;
    }

    private int toInteger(Object p) {

        if (p instanceof Integer) {
            return ((Integer) p).intValue();
        }
        if (p instanceof Character) {
            return ((Character) p).charValue();
        }
        if (p instanceof Boolean) {
            return ((Boolean) p).booleanValue() ? 1 : 0;
        }
        if (p instanceof Number) {
            return ((Number) p).intValue();
        }
        if (p instanceof String) {
            return Integer.parseInt((String) p);
        }
        throw new IllegalArgumentException();
    }

}
