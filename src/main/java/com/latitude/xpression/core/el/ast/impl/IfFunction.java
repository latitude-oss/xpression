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

package com.latitude.xpression.core.el.ast.impl;

import java.math.BigDecimal;

import com.latitude.xpression.core.el.ast.AbstractLazyFunction;
import com.latitude.xpression.core.el.ast.LazyObject;
import com.latitude.xpression.core.el.ast.LazyParams;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class IfFunction extends AbstractLazyFunction {

    public IfFunction(String name) {
        super(name, Type.ANY, Type.ANY, 3);
    }

    @Override
    public LazyObject lazyEval(LazyParams lazyParams) {
        LazyObject parameter = lazyParams.get(0);
        Preconditions.notNull(parameter, "At least one parameter is required");
        Object result = parameter.eval();
        boolean isTrue = !result.equals(BigDecimal.ZERO) || result.equals(Boolean.TRUE);
        return isTrue ? lazyParams.get(1) : lazyParams.get(2);
    }

}
