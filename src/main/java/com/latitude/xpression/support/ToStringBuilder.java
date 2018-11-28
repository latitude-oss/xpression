package com.latitude.xpression.support;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ToStringBuilder {

    private final StringBuilder sb = new StringBuilder(32);

    private final Object object;

    private final boolean formatted;

    public static String reflection(Object object) {
        return reflection(object, true);
    }

    public static String reflection(Object object, boolean formatted) {
        ToStringBuilder builder = new ToStringBuilder(object, formatted);
        Field[] fields = object.getClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        for (Field field : fields) {
            String fieldName = field.getName();
            if (field.getName().indexOf('$') != -1) {
                continue;
            }
            if (Modifier.isTransient(field.getModifiers()) || (Modifier.isStatic(field.getModifiers()))) {
                continue;
            }
            try {
                Object fieldValue = field.get(object);
                builder.append(fieldName, fieldValue);
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return builder.toString();
    }

    public ToStringBuilder(Object object) {
        this(object, true);
    }

    public ToStringBuilder(Object object, boolean formatted) {
        this.object = object;
        this.formatted = formatted;
    }

    public ToStringBuilder append(String fieldName, Object value) {
        Object tempValue = null;
        if (value == null) {
            tempValue = "<null>";
        }
        else if (value.getClass().isArray()) {
            tempValue = ToStringUtils.arrayToString((Object[]) value);
        }
        else {
            tempValue = value;
        }

        if (formatted) {
            sb.append("\t").append(fieldName).append(" = ").append(tempValue).append('\n');
        }
        else {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append(fieldName).append('=').append(tempValue);
        }
        return this;
    }

    public String build() {
        return toString();
    }

    @Override
    public String toString() {
        String className = object.getClass().getSimpleName();
        return className + (formatted ? "[\n" : "[") + sb + ']';
    }

}
