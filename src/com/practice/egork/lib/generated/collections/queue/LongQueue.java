package com.practice.egork.lib.generated.collections.queue;

import com.practice.egork.lib.generated.collections.LongCollection;

public interface LongQueue extends LongCollection {
    default public long first() {
        return peek();
    }

    public long peek();

    public long poll();
}
