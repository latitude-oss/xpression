/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

public interface ElResolver {

    public Object resolveValue(ElContext context, Object target, Object property);

}
