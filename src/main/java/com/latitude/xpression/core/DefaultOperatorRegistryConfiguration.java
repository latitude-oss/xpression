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

import com.latitude.xpression.OperatorRegistryConfiguration;
import com.latitude.xpression.core.context.OperatorRegistry;
import com.latitude.xpression.core.el.ast.impl.AndOperator;
import com.latitude.xpression.core.el.ast.impl.DivisionOperator;
import com.latitude.xpression.core.el.ast.impl.EqualsOperator;
import com.latitude.xpression.core.el.ast.impl.GtOperator;
import com.latitude.xpression.core.el.ast.impl.GteOperator;
import com.latitude.xpression.core.el.ast.impl.LtOperator;
import com.latitude.xpression.core.el.ast.impl.LteOperator;
import com.latitude.xpression.core.el.ast.impl.ModulusOperator;
import com.latitude.xpression.core.el.ast.impl.MultiplicationOperator;
import com.latitude.xpression.core.el.ast.impl.NegativeOperator;
import com.latitude.xpression.core.el.ast.impl.NotEqualsOperator;
import com.latitude.xpression.core.el.ast.impl.OrOperator;
import com.latitude.xpression.core.el.ast.impl.PositiveOperator;
import com.latitude.xpression.core.el.ast.impl.PowerOperator;
import com.latitude.xpression.core.el.ast.impl.SubtractionOperator;
import com.latitude.xpression.core.el.ast.impl.SumOperator;
import com.latitude.xpression.support.Preconditions;

public class DefaultOperatorRegistryConfiguration implements OperatorRegistryConfiguration {

    private final MathContext mathContext;

    public DefaultOperatorRegistryConfiguration(MathContext mathContext) {
        Preconditions.notNull(mathContext, "MathContext is required");
        this.mathContext = mathContext;
    }

    @Override
    public OperatorRegistry configure(OperatorRegistry operatorRegistry) {

        operatorRegistry.register(new EqualsOperator("=")).withAlias("==").withAlias("eq");
        operatorRegistry.register(new NotEqualsOperator("!=")).withAlias("<>").withAlias("neq");
        operatorRegistry.register(new AndOperator("&")).withAlias("&&").withAlias("and");
        operatorRegistry.register(new OrOperator("|")).withAlias("||").withAlias("or");
        operatorRegistry.register(new GtOperator(">")).withAlias("gt");
        operatorRegistry.register(new GteOperator(">=")).withAlias("gte");
        operatorRegistry.register(new LtOperator("<")).withAlias("lt");
        operatorRegistry.register(new LteOperator("<=")).withAlias("lte");
        operatorRegistry.register(new PositiveOperator("+"));
        operatorRegistry.register(new NegativeOperator("-"));

        operatorRegistry.register(new SumOperator("+", mathContext));
        operatorRegistry.register(new SubtractionOperator("-", mathContext));
        operatorRegistry.register(new MultiplicationOperator("*", mathContext));
        operatorRegistry.register(new DivisionOperator("/", mathContext));
        operatorRegistry.register(new ModulusOperator("%", mathContext));
        operatorRegistry.register(new PowerOperator("^", mathContext));

        return operatorRegistry;
    }

}
