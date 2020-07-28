package com.practice.egork.lib.generated.collections.set;

import com.practice.egork.lib.generated.collections.LongCollection;

public interface LongSet extends LongCollection {
    @Override
    default public int count(long value) {
        return contains(value) ? 1 : 0;
    }
}
