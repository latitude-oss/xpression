/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core.el;

import java.util.Optional;

public enum Braket {

    OPEN_ROUND('(', true), OPEN_SQUARE('[', true), OPEN_CURLY('{', true), CLOSED_ROUND(')', false), CLOSED_SQUARE(']',
            false), CLOSED_CURLY('}', false);

    private final char symbol;

    private final boolean open;

    private Braket(char symbol, boolean open) {
        this.symbol = symbol;
        this.open = open;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public boolean sameSymbol(char symbol) {
        return this.symbol == symbol;
    }

    public boolean isOpen() {
        return open;
    }

    public static Optional<Braket> tryFind(char symbol) {
        for (Braket braket : values()) {
            if (braket.sameSymbol(symbol)) {
                return Optional.of(braket);
            }
        }
        return Optional.empty();
    }

    public static boolean isBraket(char symbol) {
        return tryFind(symbol).isPresent();
    }

    public static boolean isOpenBraket(char symbol) {
        Optional<Braket> braketHolder = tryFind(symbol);
        if (braketHolder.isPresent()) {
            Braket braket = braketHolder.get();
            return braket.isOpen();
        }
        return false;
        // throw new IllegalArgumentException(String.format("Symbol %s is not a
        // braket", symbol));
    }

    public static boolean isClosedBraket(char symbol) {
        Optional<Braket> braketHolder = tryFind(symbol);
        if (braketHolder.isPresent()) {
            Braket braket = braketHolder.get();
            return !braket.isOpen();
        }
        return false;
    }

}
