package com.latitude.xpression.support.conversion.impl;

/**
 * @author
 *
 * @param <S>
 * @param <T>
 */
public interface Converter<S, T> {

    /**
     * @param source
     * @return
     */
    T convert(S source);

}
