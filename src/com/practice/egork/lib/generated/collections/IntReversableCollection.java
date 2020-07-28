package com.practice.egork.lib.generated.collections;

import com.practice.egork.lib.generated.collections.iterator.IntIterator;

public interface IntReversableCollection extends IntCollection {
    //abstract
    public IntIterator reverseIterator();

    //base
    default public int last() {
        return reverseIterator().value();
    }

    default IntStream reversed() {
        return () -> reverseIterator();
    }
}
