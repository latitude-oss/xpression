/*
 * Copyright 2018 Latitude Srls
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
