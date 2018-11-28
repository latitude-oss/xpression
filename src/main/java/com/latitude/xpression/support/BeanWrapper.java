package com.latitude.xpression.support;

import com.latitude.xpression.core.el.bean.BeanPropertyAccessException;
import com.latitude.xpression.core.el.bean.BeanPropertyAccessStrategy;
import com.latitude.xpression.core.el.bean.PropertyDescriptorAccessStrategy;

public class BeanWrapper {

    private final Object target;

    private final BeanPropertyAccessStrategy accessStrategy;

    private BeanPropertyAccessStrategy fallbackStrategy;

    public static BeanWrapper target(Object target) {
        return new BeanWrapper(target);
    }

    public static BeanWrapper target(Object target, BeanPropertyAccessStrategy fallbackAccessStrategy) {
        return new BeanWrapper(target, fallbackAccessStrategy);
    }

    public BeanWrapper(Object target) {
        this(target, null);
    }

    public BeanWrapper(Object target, BeanPropertyAccessStrategy fallbackAccessStrategy) {
        Preconditions.notNull(target, "Object target");
        this.target = target;
        this.accessStrategy = new PropertyDescriptorAccessStrategy(target);
        this.fallbackStrategy = fallbackAccessStrategy;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetType() {
        return target.getClass();
    }

    @SuppressWarnings("unchecked")
    public <T> T evaluatePath(String path) {
        BeanWrapper beanWrapper = this;
        String propertyName = null;
        if (path.indexOf('.') > 0) {
            String[] pathArray = path.split("\\.");
            for (int i = 0; i < pathArray.length; i++) {
                propertyName = pathArray[i];
                if (i < pathArray.length - 1) {
                    Class<?> propertyType = ReflectionUtils.resolveFieldType(beanWrapper.getTargetType(), propertyName);
                    beanWrapper = beanWrapper.wrapProperty(propertyType, propertyName);
                }
            }
        }
        else {
            propertyName = path;
        }
        Class<?> propertyType = ReflectionUtils.resolveFieldType(beanWrapper.getTargetType(), propertyName);
        return (T) beanWrapper.getPropertyValue(propertyType, propertyName);
    }

    public <T> BeanWrapper wrapProperty(Class<T> propertyType, String propertyName) {
        return new BeanWrapper(getPropertyValue(propertyType, propertyName), fallbackStrategy);
    }

    public <T> T getPropertyValue(Class<T> propertyType, String propertyName) {
        try {
            return accessStrategy.readProperty(target, propertyType, propertyName);
        }
        catch (BeanPropertyAccessException exception) {
            if (fallbackStrategy != null) {
                return fallbackStrategy.readProperty(target, propertyType, propertyName);
            }
            throw exception;
        }
    }

    public void setPropertyValue(String propertyName, Object value) {
        try {
            accessStrategy.writeProperty(target, propertyName, value);
        }
        catch (BeanPropertyAccessException exception) {
            if (fallbackStrategy != null) {
                fallbackStrategy.writeProperty(target, propertyName, value);
            }
            else {
                throw exception;
            }
        }
    }

    public void setFallbackStrategy(BeanPropertyAccessStrategy fallbackStrategy) {
        this.fallbackStrategy = fallbackStrategy;
    }

}
