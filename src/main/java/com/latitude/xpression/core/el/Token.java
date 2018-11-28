package com.latitude.xpression.core.el;

public class Token {

    private TokenType type;

    private String surface;

    private int pos;

    public Token() {
        this.surface = new String();
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public boolean sameTypeOf(TokenType tokenType) {
        return this.type == tokenType;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void append(char c) {
        surface += c;
    }

    public int indexOf(char c) {
        return surface.indexOf(c);
    }

    public boolean containsChar(char c) {
        return indexOf(c) > -1;
    }

    public boolean containsChar(CharSequence chars) {
        for (int i = 0; i < chars.length(); i++) {
            if (containsChar(chars.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void append(String s) {
        surface += s;
    }

    public char charAt(int pos) {
        return surface.charAt(pos);
    }

    public int length() {
        return surface.length();
    }

    @Override
    public String toString() {
        return surface;
    }

}
