/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.context;

import java.util.Optional;

public interface VariableRegistry {

    public Object put(String name, Object value);

    public Optional<Object> tryFindVariable(String name);

    public void clear();

}
