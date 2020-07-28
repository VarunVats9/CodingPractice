package com.practice.egork.lib.generated.collections.set;

import com.practice.egork.lib.generated.collections.CharCollection;

public interface CharSet extends CharCollection {
    @Override
    default public int count(char value) {
        return contains(value) ? 1 : 0;
    }
}
