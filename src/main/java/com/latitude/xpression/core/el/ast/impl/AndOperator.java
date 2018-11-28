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

import com.latitude.xpression.core.el.ast.Operator;
import com.latitude.xpression.core.el.ast.Type;
import com.latitude.xpression.support.Preconditions;

public class AndOperator extends Operator {

    public AndOperator(String name) {
        super(name, Type.BOOLEAN, Type.BOOLEAN, 4, false);
    }

    @Override
    public Object eval(Object v1, Object v2) {
        Preconditions.notNull(v1, "Left operand is required!");
        Preconditions.notNull(v2, "Right operand is required!");

        boolean b1 = !v1.equals(Boolean.FALSE);
        boolean b2 = !v2.equals(Boolean.FALSE);
        return b1 && b2 ? Boolean.TRUE : Boolean.FALSE;
    }

}
