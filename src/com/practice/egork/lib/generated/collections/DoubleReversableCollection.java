package com.practice.egork.lib.generated.collections;

import com.practice.egork.lib.generated.collections.iterator.DoubleIterator;

public interface DoubleReversableCollection extends DoubleCollection {
    //abstract
    public DoubleIterator reverseIterator();

    //base
    default public double last() {
        return reverseIterator().value();
    }

    default DoubleStream reversed() {
        return () -> reverseIterator();
    }
}
