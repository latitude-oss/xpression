package com.latitude.xpression.support;

import java.lang.reflect.Method;

public class BeanUtils {

    private BeanUtils() {
    }

    private static final String BEAN_CLASS_REQUIRED = "Bean class is required";

    public static boolean isProperty(Class<?> beanClass, String beanProperty) {
        Preconditions.notNull(beanClass, BEAN_CLASS_REQUIRED);
        Preconditions.notNull(beanProperty, BEAN_CLASS_REQUIRED);
        return ReflectionUtils.findGetter(beanClass, beanProperty) != null;
    }

    public static boolean isPropertyAssignableFrom(Class<?> beanClass, String beanProperty, Class<?> type) {
        Preconditions.notNull(beanClass, BEAN_CLASS_REQUIRED);
        Preconditions.notNull(beanProperty, BEAN_CLASS_REQUIRED);
        Method method = ReflectionUtils.findGetter(beanClass, beanProperty);
        if (method == null) {
            return false;
        }
        return type.isAssignableFrom(method.getReturnType());
    }

    public static boolean isPropertyOfSameType(Class<?> beanClass, String beanProperty, Class<?> type) {
        Preconditions.notNull(beanClass, BEAN_CLASS_REQUIRED);
        Preconditions.notNull(beanProperty, BEAN_CLASS_REQUIRED);
        Method method = ReflectionUtils.findGetter(beanClass, beanProperty);
        if (method == null) {
            return false;
        }
        return method.getReturnType().getClass().equals(type);
    }

}
