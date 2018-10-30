package com.practice.emaxx.stringprocessing.stringhashing;

/**
 * Created by vvats on 30/10/18.
 */
public class RollingHash {

    public long computeHash(final String target) {

        long hash = 0;
        long pow = 1;
        int prime = 31; // We take prime, nearest to the size of the set of alphabets (lower case, 26).
        long mod = 1000000009L;

        for (int i = 0; i < target.length(); i++) {
            hash = (hash + (target.charAt(i) - 'a' + 1) * pow) % mod;
            pow = (pow * prime) % mod;
        }

        return hash;
    }

    public static void main(String[] args) {

        final RollingHash rollingHash = new RollingHash();
        System.out.println(rollingHash.computeHash("ankita"));

    }
}
