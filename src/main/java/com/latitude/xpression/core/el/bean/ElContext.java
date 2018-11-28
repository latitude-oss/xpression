/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

public class ElContext {

    private boolean propertyResolved;

    public ElContext() {
    }

    public boolean isPropertyResolved() {
        return propertyResolved;
    }

    public void setPropertyResolved(boolean propertyResolved) {
        this.propertyResolved = propertyResolved;
    }

}
