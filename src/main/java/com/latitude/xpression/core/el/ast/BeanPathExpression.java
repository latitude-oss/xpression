/*
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

import java.util.Optional;

import com.latitude.xpression.core.ExpressionException;
import com.latitude.xpression.core.context.VariableContext;
import com.latitude.xpression.core.el.bean.BeanExpressionToken;
import com.latitude.xpression.core.el.bean.BeanExpressionTokenizer;
import com.latitude.xpression.core.el.bean.ElContext;
import com.latitude.xpression.core.el.bean.ElResolver;

public class BeanPathExpression extends LazyObject {

    private final VariableContext variableContext;

    private final ElResolver elResolver;

    private final BeanExpressionTokenizer tokenizer;

    public BeanPathExpression(String expression, VariableContext variableContext, ElResolver elResolver) {
        this.variableContext = variableContext;
        this.elResolver = elResolver;
        tokenizer = new BeanExpressionTokenizer(expression);
    }

    @Override
    public Object eval() {
        BeanExpressionToken token = tokenizer.next();
        Optional<Object> variableHolder = variableContext.tryResolveVariable(token.getValue());
        if (!variableHolder.isPresent()) {
            throw new ExpressionException("Unknown variable " + token.getValue());
        }
        Object value = variableHolder.get();
        ElContext context = new ElContext();
        while (tokenizer.hasNext()) {
            token = tokenizer.next();
            context.setPropertyResolved(false);
            String property = token.getValue();
            value = elResolver.resolveValue(context, value, property);
        }
        return value;
    }

}
