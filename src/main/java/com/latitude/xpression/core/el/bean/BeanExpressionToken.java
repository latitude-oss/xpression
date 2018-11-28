/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

public class BeanExpressionToken {

    private final String value;

    private TokenType tokenType;

    public BeanExpressionToken(String value) {
        this.value = value;
    }

    public BeanExpressionToken(String value, TokenType tokenType) {
        this.value = value;
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String asText() {
        return tokenType.asText(value);
    }

    @Override
    public String toString() {
        return asText();
    }

    public static enum TokenType {

        VARIABLE("%s"), ARRAY_ACCESS("[%s]"), LIST_ACCESS("(%s)"), MAP_ACCESS("['%s']"), BEAN_PROPERTY_ACCESS("%s");

        private final String textPattern;

        private TokenType(String textPattern) {
            this.textPattern = textPattern;
        }

        public String asText(String value) {
            return String.format(textPattern, value);
        }

    }

}
