package com.practice.egork.lib.collections.comparators;

import com.practice.egork.lib.generated.collections.pair.IntIntPair;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ComparatorUtils {

    public static java.util.Comparator<IntIntPair> sortIntIntPairAsc = (a, b) -> {
        if (a.second > b.second) return 1;
        else if (a.second < b.second) return -1;
        else return 0;
    };

    public static java.util.Comparator<Integer> sortIntAsc = (a, b) -> {
        if (a > b) return 1;
        else if (a < b) return -1;
        else return 0;
    };
}