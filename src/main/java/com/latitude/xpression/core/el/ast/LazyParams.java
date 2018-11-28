/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class LazyParams extends ArrayList<LazyObject> {

    private static final long serialVersionUID = 8591039240313516452L;

    public static LazyParams variableLazyParams() {
        return LazyParams.ofSize(0);
    }

    public static LazyParams ofSize(int size) {
        return new LazyParams(size);
    }

    public LazyParams() {
        super();
    }

    public LazyParams(Collection<? extends LazyObject> list) {
        super(list);
    }

    public LazyParams(int size) {
        super(size);
    }

    public Optional<LazyObject> tryGetParam(int index) {
        if (index >= 0 && index < size()) {
            return Optional.ofNullable(get(index));
        }
        return Optional.empty();
    }

}
