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
import java.math.MathContext;
import java.util.List;

import com.latitude.xpression.core.el.ast.MathFunction;
import com.latitude.xpression.support.Preconditions;

public class RoundFunction extends MathFunction {

    public RoundFunction(String name, MathContext mathContext) {
        super(name, -1, mathContext);
    }

    @Override
    public Object doEvaluation(List<Object> parameters) {
        Preconditions.notNull(parameters.get(0), "First argument may not be null");
        BigDecimal toRound = (BigDecimal) parameters.get(0);
        Integer precisionAsInteger = 1;
        if (parameters.size() > 1) {
            BigDecimal precision = (BigDecimal) parameters.get(1);
            precisionAsInteger = precision != null ? precision.intValue() : 1;
        }
        toRound.setScale(precisionAsInteger, mathContext.getRoundingMode());
        return toRound.round(mathContext);
    }

}
