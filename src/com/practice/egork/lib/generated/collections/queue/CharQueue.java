package com.practice.egork.lib.generated.collections.queue;

import com.practice.egork.lib.generated.collections.CharCollection;

public interface CharQueue extends CharCollection {
    default public char first() {
        return peek();
    }

    public char peek();

    public char poll();
}
