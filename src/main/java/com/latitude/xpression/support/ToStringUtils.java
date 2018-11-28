package com.latitude.xpression.support;

import java.lang.reflect.Array;

public class ToStringUtils {

    private ToStringUtils() {
        super();
    }

    public static String arrayToString(Object[] array) {
        return arrayToString(array, 10);
    }

    public static String arrayToString(Object[] array, int maxCount) {
        int length = Array.getLength(array);
        StringBuilder str = new StringBuilder(32);
        str.append('[');
        for (int i = 0; i < length; i++) {
            if (i >= maxCount) {
                str.append(",...");
                break;
            }
            if (i > 0) {
                str.append(',');
            }
            str.append(Array.get(array, i));
        }
        str.append(']');
        return str.toString();
    }

}
