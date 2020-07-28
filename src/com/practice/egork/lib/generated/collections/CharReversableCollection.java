package com.practice.egork.lib.generated.collections;

import com.practice.egork.lib.generated.collections.iterator.CharIterator;

public interface CharReversableCollection extends CharCollection {
    //abstract
    public CharIterator reverseIterator();

    //base
    default public char last() {
        return reverseIterator().value();
    }

    default CharStream reversed() {
        return () -> reverseIterator();
    }
}
