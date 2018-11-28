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

import com.latitude.xpression.support.Preconditions;

public abstract class UnaryOperator extends Operator {

    public UnaryOperator(String name, Type inputType, Type outputType, int precedence, boolean leftAssociative) {
        super(name, inputType, outputType, precedence, leftAssociative);
    }

    @Override
    public Object eval(Object v1, Object v2) {
        Preconditions.notNull(v1, "Left operator is required");
        Preconditions.state(v2 == null, "Did not expect a second parameter for unary operator");
        return evalUnary(v1);
    }

    abstract public Object evalUnary(Object v1);

}
