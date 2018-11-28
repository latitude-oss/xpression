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

package com.latitude.xpression.core;

import java.math.MathContext;

import com.latitude.xpression.FunctionRegistryConfiguration;
import com.latitude.xpression.core.context.FunctionRegistry;
import com.latitude.xpression.core.el.ast.impl.BeanPropertyFunction;
import com.latitude.xpression.core.el.ast.impl.IfFunction;
import com.latitude.xpression.core.el.ast.impl.NotFunction;
import com.latitude.xpression.core.el.ast.impl.RoundFunction;
import com.latitude.xpression.core.el.ast.impl.SqrtFunction;
import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.conversion.ConversionService;

public class DefaultFunctionRegistryConfiguration implements FunctionRegistryConfiguration {

    private final ConversionService conversionService;

    private final MathContext mathContext;

    public DefaultFunctionRegistryConfiguration(ConversionService conversionService, MathContext mathContext) {
        Preconditions.notNull(conversionService, "ConversionService is required");
        Preconditions.notNull(mathContext, "MathContext is required");
        this.conversionService = conversionService;
        this.mathContext = mathContext;
    }

    @Override
    public FunctionRegistry configure(FunctionRegistry functionRegistry) {
        functionRegistry.register(new NotFunction("NOT").setConversionService(conversionService));
        functionRegistry.register(new IfFunction("IF").setConversionService(conversionService));
        functionRegistry.register(new SqrtFunction("SQRT", mathContext).setConversionService(conversionService));
        functionRegistry.register(new RoundFunction("ROUND", mathContext).setConversionService(conversionService));
        functionRegistry.register(new BeanPropertyFunction("beanProperty").setConversionService(conversionService))
                .withAlias("bp").withAlias("property").withAlias("prop").withAlias("jsonPath");
        return functionRegistry;
    }

}
