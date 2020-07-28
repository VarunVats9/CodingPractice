package com.practice.egork.lib.generated.collections.set;

import com.practice.egork.lib.generated.collections.IntCollection;

public interface IntSet extends IntCollection {
    @Override
    default public int count(int value) {
        return contains(value) ? 1 : 0;
    }
}
