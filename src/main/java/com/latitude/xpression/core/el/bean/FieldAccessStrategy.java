package com.latitude.xpression.core.el.bean;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.latitude.xpression.support.Preconditions;
import com.latitude.xpression.support.ReflectionUtils;

public class FieldAccessStrategy implements BeanPropertyAccessStrategy {

    private static final String ACCESS_EXCEPTION_MESSAGE = "Cannot access to property %s on class %s";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void writeProperty(Object target, String propertyName, Object value) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Trying fallback field access strategy");
            }
            Field propertyField = ReflectionUtils.findField(target.getClass(), propertyName);
            Class<?> expectedType = propertyField.getType();
            if (expectedType.isAssignableFrom(value.getClass())) {
                tryWriteField(target, propertyField, value);
            }
            else {
                Throwable cause = new ClassCastException(String.format("Cannot cast actual type %s to expected type %s",
                        value.getClass(), expectedType));
                throw new BeanPropertyAccessException(
                        String.format(ACCESS_EXCEPTION_MESSAGE, propertyName, target.getClass()), cause);
            }
        }
        catch (UndeclaredThrowableException exception) {
            if (exception.getUndeclaredThrowable() instanceof NoSuchFieldException && logger.isWarnEnabled()) {
                logger.warn("Unrecognized field {} on class {}", propertyName, target.getClass(), exception);
            }
            else {
                if (logger.isErrorEnabled()) {
                    logger.error("An error occurred writing property", exception);
                }
            }
            throw new BeanPropertyAccessException(
                    String.format(ACCESS_EXCEPTION_MESSAGE, propertyName, target.getClass()));
        }
    }

    @Override
    public <T> T readProperty(Object target, Class<T> propertyType, String propertyName) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Trying fallback field access strategy");
            }
            Field propertyField = ReflectionUtils.findField(target.getClass(), propertyName);
            Object value = readField(target, propertyField);
            if (value == null) {
                throw new ClassCastException(
                        String.format("Cannot cast value is NULL to expected type %s", propertyType));
            }
            else if (propertyType.isAssignableFrom(value.getClass())) {
                return propertyType.cast(value);
            }
            Throwable cause = new ClassCastException(
                    String.format("Cannot cast actual type %s to expected type %s", value.getClass(), propertyType));
            throw new BeanPropertyAccessException(
                    String.format(ACCESS_EXCEPTION_MESSAGE, propertyName, target.getClass()), cause);
        }
        catch (UndeclaredThrowableException exception) {
            if (exception.getUndeclaredThrowable() instanceof NoSuchFieldException && logger.isWarnEnabled()) {
                logger.warn("Unrecognized field {} on class {}", propertyName, target.getClass(), exception);
            }
            else {
                if (logger.isErrorEnabled()) {
                    logger.error("An error occurred reading property", exception);
                }
            }
            throw new BeanPropertyAccessException(
                    String.format(ACCESS_EXCEPTION_MESSAGE, propertyName, target.getClass()));
        }
    }

    private Object readField(Object target, Field field) {
        Preconditions.notNull(field, "Field is required");
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.get(target);
        }
        catch (Exception exception) {
            if (logger.isWarnEnabled()) {
                logger.warn("Unable to read field {} on class {}", field.getName(), target.getClass(), exception);
            }
            return null;
        }
    }

    private void tryWriteField(Object target, Field field, Object value) {
        Preconditions.notNull(field, "Field is required");
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(target, value);
        }
        catch (Exception exception) {
            if (logger.isWarnEnabled()) {
                logger.warn("Unable to write field {} on class {}", field.getName(), target.getClass(), exception);
            }
        }
    }

}
