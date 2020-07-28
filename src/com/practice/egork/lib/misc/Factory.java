package com.practice.egork.lib.misc;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Factory<V> {
    public V create();
}
