package com.practice.egork.lib.generated.collections.hashing;

/**
 * @author egor@egork.net
 */
public class DoubleHash {
    private DoubleHash() {
    }

    public static int hash(double c) {
        long bits = Double.doubleToLongBits(c);
        return LongHash.hash(bits);
    }
}
