/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

import java.util.List;

public class ListElResolver implements ElResolver {

    @Override
    public Object resolveValue(ElContext context, Object target, Object property) {
        if (target != null && target instanceof List) {
            List<?> list = (List<?>) target;
            int index = toInteger(property);
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds of the list", index));
                // return null;
            }
            context.setPropertyResolved(true);
            return list.get(index);
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
