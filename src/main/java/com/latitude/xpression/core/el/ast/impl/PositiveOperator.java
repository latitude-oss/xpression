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

import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.core.el.ast.UnaryOperator;
import com.latitude.xpression.support.Preconditions;

public class PositiveOperator extends UnaryOperator {

    public PositiveOperator(String name) {
        super(name, Type.NUMERIC, Type.NUMERIC, 60, false);
    }

    @Override
    public Object evalUnary(Object v1) {
        Preconditions.notNull(v1, "Left operator is required");
        BigDecimal value = (BigDecimal) v1;
        return value.multiply(BigDecimal.ONE);
    }

}
