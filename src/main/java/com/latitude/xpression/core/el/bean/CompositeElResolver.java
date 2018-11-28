/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This implementation is thread-safe
 * 
 * @author Vincenzo Autiero
 *
 */
public class CompositeElResolver implements ElResolver {

    private static final Object MUTEX = new Object();

    private Collection<ElResolver> elResolvers;

    public CompositeElResolver() {
        elResolvers = new ArrayList<ElResolver>();
    }

    public void add(ElResolver resolver) {
        elResolvers.add(resolver);
    }

    @Override
    public Object resolveValue(ElContext context, Object target, Object property) {
        synchronized (MUTEX) {
            for (ElResolver resolver : elResolvers) {
                Object value = resolver.resolveValue(context, target, property);
                if (context.isPropertyResolved()) {
                    return value;
                }
            }
            return null;
        }
    }

}
