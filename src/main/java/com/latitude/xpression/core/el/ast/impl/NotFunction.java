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
import java.util.List;

import com.latitude.xpression.core.el.ast.AbstractFunction;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class NotFunction extends AbstractFunction {

    public NotFunction(String name) {
        super(name, Type.BOOLEAN, Type.BOOLEAN, 1);
    }

    @Override
    public Object doEvaluation(List<Object> parameters) {
        Object parameter = parameters.get(0);
        Preconditions.notNull(parameter, "Parameter at index 0 is required");
        boolean zero = BigDecimal.ZERO.equals(parameter) || Boolean.FALSE.equals(parameter);
        return zero ? Boolean.TRUE : Boolean.FALSE;
    }

}
