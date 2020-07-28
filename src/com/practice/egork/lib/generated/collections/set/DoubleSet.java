package com.practice.egork.lib.generated.collections.set;

import com.practice.egork.lib.generated.collections.DoubleCollection;

public interface DoubleSet extends DoubleCollection {
    @Override
    default public int count(double value) {
        return contains(value) ? 1 : 0;
    }
}
