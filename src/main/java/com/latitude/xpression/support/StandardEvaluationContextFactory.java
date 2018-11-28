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

package com.latitude.xpression.support;

import com.latitude.xpression.core.context.EvaluationContext;
import com.latitude.xpression.core.context.FunctionRegistry;
import com.latitude.xpression.core.context.OperatorRegistry;
import com.latitude.xpression.core.context.StandardEvaluationContext;
import com.latitude.xpression.core.context.VariableRegistryTreeMapImpl;

public class StandardEvaluationContextFactory implements EvaluationContextFactory {

    private final FunctionRegistry functionRegistry;

    private final OperatorRegistry operatorRegistry;

    public StandardEvaluationContextFactory(FunctionRegistry functionRegistry, OperatorRegistry operatorRegistry) {
        Preconditions.notNull(functionRegistry, "FunctionRegistry is required");
        Preconditions.notNull(operatorRegistry, "OperatorRegistry is required");
        this.functionRegistry = functionRegistry;
        this.operatorRegistry = operatorRegistry;
    }

    @Override
    public EvaluationContext create() {
        return new StandardEvaluationContext(functionRegistry, operatorRegistry, new VariableRegistryTreeMapImpl());
    }

}
