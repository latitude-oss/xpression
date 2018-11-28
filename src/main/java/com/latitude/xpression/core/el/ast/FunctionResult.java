package com.latitude.xpression.core.el.ast;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionResult extends LazyObject {

    private final AbstractFunction function;

    private final Collection<LazyObject> lazyParams;

    public FunctionResult(String text, AbstractFunction function, Collection<LazyObject> lazyParams) {
        super(text);
        this.function = function;
        this.lazyParams = lazyParams;
    }

    @Override
    public Object eval() {
        List<Object> parameters = lazyParams.stream().map(new Function<LazyObject, Object>() {
            public Object apply(LazyObject input) {
                return input.eval();
            }
        }).collect(Collectors.toList());
        return function.eval(parameters);
    }

    @Override
    public String getText() {
        return String.valueOf(eval());
    }

}
