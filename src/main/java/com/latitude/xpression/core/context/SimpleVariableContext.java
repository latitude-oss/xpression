package com.latitude.xpression.core.context;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.latitude.xpression.support.ToStringBuilder;

/**
 * This implementation holds the context of the variables. It's thread-safe.
 * 
 * @author Vincenzo Autiero
 *
 */
public class SimpleVariableContext implements VariableContext {

    private final Map<String, Object> variableContext;

    public SimpleVariableContext() {
        variableContext = new ConcurrentHashMap<String, Object>();
    }

    @Override
    public void putVariable(String name, Object value) {
        variableContext.put(name, value);
    }

    @Override
    public Optional<Object> tryResolveVariable(String variableName) {
        if (variableContext.containsKey(variableName)) {
            return Optional.ofNullable(variableContext.get(variableName));
        }
        return Optional.empty();
    }

    @Override
    public Set<VariableContext.Entry> entrySet() {
        return entryStream().collect(Collectors.<VariableContext.Entry> toSet());
    }

    @Override
    public Iterator<VariableContext.Entry> iterator() {
        return entryStream().iterator();
    }

    @Override
    public void clear() {
        variableContext.clear();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, false);
        for (Map.Entry<String, Object> entry : variableContext.entrySet()) {
            builder.append(entry.getKey(), entry.getValue());
        }
        return builder.toString();
    }

    private Stream<VariableContext.Entry> entryStream() {
        return variableContext.entrySet().stream().map(new EntryConverter());
    }

    private static class EntryConverter implements Function<Map.Entry<String, Object>, VariableContext.Entry> {
        @Override
        public VariableContext.Entry apply(java.util.Map.Entry<String, Object> entry) {
            return new Entry(entry.getKey(), entry.getValue());
        }
    }

}
