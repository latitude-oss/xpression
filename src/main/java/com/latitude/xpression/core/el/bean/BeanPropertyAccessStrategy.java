package com.latitude.xpression.core.el.bean;

public interface BeanPropertyAccessStrategy {

    public void writeProperty(Object target, String propertyName, Object value);

    public <T> T readProperty(Object target, Class<T> propertyType, String propertyName);

}
