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

package com.latitude.xpression;

import com.latitude.xpression.core.context.EvaluationContext;

public interface Expression {

    public String asText();

    public boolean isBoolean(EvaluationContext evaluationContext);

    public Object evaluate(EvaluationContext evaluationContext);

    public <T> T evaluateAs(Class<T> type, EvaluationContext evaluationContext);

    public String evaluateAsText(EvaluationContext evaluationContext);

}
