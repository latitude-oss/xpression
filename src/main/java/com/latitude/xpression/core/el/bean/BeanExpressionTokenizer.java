/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el.bean;

import java.util.Iterator;
import java.util.Optional;

import com.latitude.xpression.core.el.Braket;
import com.latitude.xpression.core.el.Constants;
import com.latitude.xpression.core.el.bean.BeanExpressionToken.TokenType;
import com.latitude.xpression.support.CharacterUtils;
import com.latitude.xpression.support.Preconditions;

public class BeanExpressionTokenizer implements Iterator<BeanExpressionToken> {

    private final String surface;

    private int position = -1;

    private BeanExpressionToken previousToken = null;

    public BeanExpressionTokenizer(String surface) {
        if (surface == null || surface.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        this.surface = surface;
    }

    protected String getSurface() {
        return surface;
    }

    public boolean hasNext() {
        return hasMoreChars();
    }

    public BeanExpressionToken next() {
        if (hasMoreChars()) {
            String value = "";
            TokenType tokenType;
            if (!isBeanAccessExpressionChar(peekNext()) && previousToken == null) {
                while (!isBeanAccessExpressionChar(peekNext()) && Constants.NULL_CHAR != peekNext()) {
                    value += pullNext();
                }
                tokenType = TokenType.VARIABLE;
            }
            else {
                if (isBraket(peekNext())) {
                    Optional<Braket> braketHolder = Braket.tryFind(pullNext());
                    Braket braket = braketHolder.get();
                    Preconditions.state(braket.isOpen(),
                            String.format("Expected open braket at %s on %s", position, surface));
                    if (Braket.OPEN_ROUND == braket) {
                        Preconditions.state(isDigit(peekNext()),
                                String.format("Expected digit at %s on %s", position, surface));
                        tokenType = TokenType.LIST_ACCESS;
                        while (!isBeanAccessExpressionChar(peekNext()) && Constants.NULL_CHAR != peekNext()) {
                            value += pullNext();
                        }
                        char expectedClosedSquare = pullNext();
                        Preconditions.state(isBraket(expectedClosedSquare),
                                String.format("Expected closed round braket at %s on %s", position, surface));
                        Braket closedBraket = Braket.tryFind(expectedClosedSquare).get();
                        Preconditions.state(Braket.CLOSED_ROUND == closedBraket,
                                String.format("Expected closed round braket at %s on %s", position, surface));
                    }
                    else {
                        Preconditions.state(Braket.OPEN_SQUARE == braket,
                                String.format("Expected open square braket '[' at %s on %s", position, surface));
                        boolean mayBeStringDelimiter = CharacterUtils.isStringDelimiter(peekNext());
                        if (mayBeStringDelimiter) {
                            char openingStringDelimiter = pullNext();
                            tokenType = TokenType.MAP_ACCESS;
                            while (!isBeanAccessExpressionChar(peekNext()) && Constants.NULL_CHAR != peekNext()) {
                                value += pullNext();
                            }
                            char closingStringDelimiter = pullNext();
                            Preconditions.state(openingStringDelimiter == closingStringDelimiter,
                                    String.format("Expected same string delimiter at %s on %s", position, surface));
                            char expectedClosedSquare = pullNext();
                            Preconditions.state(isBraket(expectedClosedSquare),
                                    String.format("Expected closed square braket at %s on %s", position, surface));
                            Braket closedBraket = Braket.tryFind(expectedClosedSquare).get();
                            Preconditions.state(Braket.CLOSED_SQUARE == closedBraket,
                                    String.format("Expected closed square braket at %s on %s", position, surface));

                        }
                        else {
                            Preconditions.state(isDigit(peekNext()),
                                    String.format("Expected digit at %s on %s", position, surface));
                            tokenType = TokenType.ARRAY_ACCESS;
                            while (!isBeanAccessExpressionChar(peekNext()) && Constants.NULL_CHAR != peekNext()) {
                                value += pullNext();
                            }

                            char expectedClosedSquare = pullNext();
                            Preconditions.state(isBraket(expectedClosedSquare),
                                    String.format("Expected closed square braket at %s on %s", position, surface));
                            Braket closedBraket = Braket.tryFind(expectedClosedSquare).get();
                            Preconditions.state(Braket.CLOSED_SQUARE == closedBraket,
                                    String.format("Expected closed square braket at %s on %s", position, surface));
                        }
                    }

                }
                else {
                    Preconditions.state(isDot(pullNext()),
                            String.format("Expected dot at %s on %s", position, surface));
                    tokenType = TokenType.BEAN_PROPERTY_ACCESS;
                    while (!isBeanAccessExpressionChar(peekNext()) && Constants.NULL_CHAR != peekNext()) {
                        value += pullNext();
                    }
                }
            }
            return previousToken = new BeanExpressionToken(value, tokenType);
        }
        throw new IllegalStateException(
                String.format("No more token on '%s' current position is (%s)", surface, position));
    }

    private boolean hasMoreChars() {
        return existsCharAt(position + 1);
    }

    private char peekCurrent() {
        return peekAt(0);
    }

    private char peekNext() {
        return peekAt(1);
    }

    private char peekAt(int index) {
        if (existsCharAt(position + index)) {
            return surface.charAt(position + index);
        }
        return Constants.NULL_CHAR;
    }

    private char pullNext() {
        if (existsCharAt(position + 1)) {
            return surface.charAt(++position);
        }
        return Constants.NULL_CHAR;
    }

    private boolean existsCharAt(int index) {
        return (index) >= 0 & (index) <= surface.length() - 1;
    }

    public boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public boolean isDot(char ch) {
        return CharacterUtils.isDot(ch);
    }

    public boolean isBraket(char ch) {
        return Braket.isBraket(ch);
    }

    public boolean isBeanAccessExpressionChar(char ch) {
        return Constants.BEAN_PATH_EXPRESSION_CHARS.indexOf(ch) >= 0;
    }

}
