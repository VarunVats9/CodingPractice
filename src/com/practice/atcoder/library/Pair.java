package com.practice.atcoder.library;

import java.util.Objects;

public class Pair {

    public int first, second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first &&
                second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
