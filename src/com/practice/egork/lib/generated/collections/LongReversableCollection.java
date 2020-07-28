package com.practice.egork.lib.generated.collections;

import com.practice.egork.lib.generated.collections.iterator.LongIterator;

public interface LongReversableCollection extends LongCollection {
    //abstract
    public LongIterator reverseIterator();

    //base
    default public long last() {
        return reverseIterator().value();
    }

    default LongStream reversed() {
        return () -> reverseIterator();
    }
}
