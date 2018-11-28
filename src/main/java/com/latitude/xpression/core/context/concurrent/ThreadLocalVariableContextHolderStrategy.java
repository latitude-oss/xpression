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

package com.latitude.xpression.core.context.concurrent;

import com.latitude.xpression.core.context.SimpleVariableContext;
import com.latitude.xpression.core.context.VariableContext;

/**
 * @author vautiero
 *
 */
public class ThreadLocalVariableContextHolderStrategy implements VariableContextHolderStrategy {

    private static final ThreadLocal<VariableContext> contextHolder = new ThreadLocal<VariableContext>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public VariableContext getContext() {
        VariableContext context = contextHolder.get();
        if (context == null) {
            context = createEmptyContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void setContext(VariableContext logContext) {
        if (logContext == null) {
            throw new IllegalArgumentException("LogContext is required");
        }
        contextHolder.set(logContext);
    }

    @Override
    public VariableContext createEmptyContext() {
        return new SimpleVariableContext();
    }

}
