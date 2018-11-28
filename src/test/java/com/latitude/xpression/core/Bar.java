/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core;

import java.util.HashMap;
import java.util.Map;

public class Bar {

    private String value;

    private Bar child;

    private Map<String, Object> data = new HashMap<String, Object>();

    public Bar(String value) {
        this(value, null);
    }

    public Bar(String value, Bar child) {
        this.value = value;
        this.child = child;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Bar getChild() {
        return child;
    }

    public void setChild(Bar child) {
        this.child = child;
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

}
