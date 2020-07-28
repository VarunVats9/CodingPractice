package com.practice.egork.lib.generated.collections.hashing;

/**
 * @author egor@egork.net
 */
public class LongHash {
    private LongHash() {
    }

    public static int hash(long c) {
        return (int) ((c >>> 32) ^ c);
    }
}
