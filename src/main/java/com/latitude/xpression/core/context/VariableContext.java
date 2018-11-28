package com.latitude.xpression.core.context;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import com.latitude.xpression.support.Preconditions;

public interface VariableContext extends Iterable<VariableContext.Entry> {

    void putVariable(String variableName, Object value);

    Optional<Object> tryResolveVariable(String variableName);

    Set<VariableContext.Entry> entrySet();

    Iterator<VariableContext.Entry> iterator();

    void clear();

    public static class Entry {

        private final String name;

        private final Object value;

        public Entry(String name, Object value) {
            Preconditions.notNull(name, "Name is required");
            Preconditions.notNull(value, "Value is required");
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }

    }

}
