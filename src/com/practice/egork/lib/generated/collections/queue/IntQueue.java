package com.practice.egork.lib.generated.collections.queue;

import com.practice.egork.lib.generated.collections.IntCollection;

public interface IntQueue extends IntCollection {
    default public int first() {
        return peek();
    }

    public int peek();

    public int poll();
}
