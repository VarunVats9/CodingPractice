package com.practice.egork.lib.generated.collections.pair;

import java.util.Comparator;

/**
 * @author Egor Kulikov
 */
public class StringIntPair implements Comparable<StringIntPair> {
    public final String first;
    public final int second;

    public static StringIntPair makePair(String first, int second) {
        return new StringIntPair(first, second);
    }

    public StringIntPair(String first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StringIntPair pair = (StringIntPair) o;

        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + Integer.hashCode(second);
        return result;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @SuppressWarnings({"unchecked"})
    public int compareTo(StringIntPair o) {
        int value = first.compareTo(o.first);
        if (value != 0) {
            return value;
        }
        return Integer.compare(second, o.second);
    }

    public static Comparator<StringIntPair> sortCharIntPairAsc = (a, b) -> {
        if (a.second > b.second) return 1;
        else if (a.second < b.second) return -1;
        else return 0;
    };

    public static Comparator<StringIntPair> sortCharIntPairDesc = (a, b) -> {
        if (a.second < b.second) return 1;
        else if (a.second > b.second) return -1;
        else return 0;
    };
}
