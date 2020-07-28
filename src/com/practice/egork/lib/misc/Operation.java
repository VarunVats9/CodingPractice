package com.practice.egork.lib.misc;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Operation<V> extends Factory<V> {
    public V operation(V first, V second);
}
