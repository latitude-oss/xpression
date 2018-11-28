/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.context;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.latitude.xpression.support.ToStringBuilder;

public class VariableRegistryTreeMapImpl extends TreeMap<String, Object> implements VariableRegistry {

    private static final long serialVersionUID = 1L;

    public VariableRegistryTreeMapImpl() {
        super(String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public Optional<Object> tryFindVariable(String name) {
        if (name != null) {
            if (containsKey(name.toLowerCase())) {
                Object value = get(name);
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        for (Map.Entry<String, Object> entry : this.entrySet()) {
            builder.append(entry.getKey(), entry.getValue());
        }
        return builder.toString();
    }

}
