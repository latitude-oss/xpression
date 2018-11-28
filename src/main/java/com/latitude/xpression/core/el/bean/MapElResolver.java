/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

import java.util.Map;

public class MapElResolver implements ElResolver {

    @Override
    public Object resolveValue(ElContext context, Object target, Object property) {
        if (target instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) target;
            if (map.containsKey(property)) {
                context.setPropertyResolved(true);
                return map.get(property);
            }
        }
        return null;
    }

}
