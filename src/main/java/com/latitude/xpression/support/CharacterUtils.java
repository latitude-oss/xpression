/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.support;

public class CharacterUtils {

    public static boolean equalsIgnoreCase(char left, char right) {
        if (left == right) {
            return true;
        }
        else {
            return Character.toLowerCase(left) == Character.toLowerCase(right);
        }
    }

    public static boolean isSpaceChar(char ch) {
        return ch == ' ';
    }

    public static boolean isComma(char ch) {
        return ch == ',';
    }

    public static boolean isDot(char ch) {
        return ch == '.';
    }

    public static boolean isStringDelimiter(char ch) {
        return ch == '"' || ch == '\'';
    }

}
