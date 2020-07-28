package com.practice.egork.lib.generated.collections.queue;

import com.practice.egork.lib.generated.collections.DoubleCollection;

public interface DoubleQueue extends DoubleCollection {
    default public double first() {
        return peek();
    }

    public double peek();

    public double poll();
}
