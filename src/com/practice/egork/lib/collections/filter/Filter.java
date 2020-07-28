package com.practice.egork.lib.collections.filter;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Filter<T> {
    public boolean accept(T value);
}
