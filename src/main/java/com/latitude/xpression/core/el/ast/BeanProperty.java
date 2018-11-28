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

import com.latitude.xpression.core.el.bean.BeanPropertyAccessStrategy;
import com.latitude.xpression.core.el.bean.FieldAccessStrategy;
import com.latitude.xpression.support.BeanWrapper;

public class BeanProperty extends LazyObject {

    private final Object target;

    private final String propertyPath;

    private final BeanPropertyAccessStrategy fallbackStrategy;

    public BeanProperty(Object target, String propertyPath) {
        this.target = target;
        this.propertyPath = propertyPath;
        this.fallbackStrategy = new FieldAccessStrategy();
    }

    @Override
    public Object eval() {
        return BeanWrapper.target(target, fallbackStrategy).evaluatePath(propertyPath);
    }

}
