/*
 * Project Governor
 * Copyright 2018 Latitude Srls - All rights reserved.
 * 
 * https://www.latitude.cloud
 *
 */
package com.latitude.xpression.core;

public class Foo {

    private Bar bar;

    private Integer age;

    public Foo(Bar bar) {
        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }

    public Foo setBar(Bar bar) {
        this.bar = bar;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Foo setAge(Integer age) {
        this.age = age;
        return this;
    }

}
