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

import java.math.MathContext;

import com.latitude.xpression.support.Preconditions;

public abstract class MathFunction extends AbstractFunction {

    protected final MathContext mathContext;

    public MathFunction(String name, int numParams, MathContext mathContext) {
        super(name, Type.NUMERIC, Type.NUMERIC, numParams);
        Preconditions.notNull(mathContext, "MathContext is required");
        this.mathContext = mathContext;
    }

}
