package com.practice.emaxx.stringprocessing.fundamentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * For reference read : https://codeforces.com/blog/entry/60445
 */
public class StringHashing {

    public long computeHash(final String target) {

        long hash = 0;
        long pow = 1;
        int prime = 31; // We take prime, nearest to the size of the set of alphabets (lower case, 26).
        long mod = 100_000_009L;

        for (int i = 0; i < target.length(); i++) {
            hash = (hash + (target.charAt(i) - 'a' + 1) * pow) % mod;
            pow = (pow * prime) % mod;
        }

        return hash;
    }

    public List<List<Integer>> groupIdenticalStrings(final List<String> list) {
        Map<Long, List<Integer>> hashToListOfIndex = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final long hash = computeHash(list.get(i));
            if (!hashToListOfIndex.containsKey(hash)) {
                hashToListOfIndex.put(hash, new ArrayList<>());
            }
            hashToListOfIndex.get(hash).add(i);
        }
        hashToListOfIndex.forEach((k, v) -> result.add(v));
        return result;
    }

    public int countUniqueSubstring(final String target) {

        int n = target.length();
        int prime = 31;
        long mod = 100_000_009L; // gcd(prime, mod) = 1.

        long[] base = new long[n+1];
        base[0] = 1;

        // Pre-compute all the base with powers.
        for (int i = 1; i <= n; i++) {
            base[i] = (base[i-1] * prime) % mod;
        }

        /*
         * Compute hash for all the prefixes.
         * Hash of the sequence : (a_0 * base_pow(0) + ....... + a_n-1 * base_pow(n-1)) mod m
         * Prefix till k [P(k)] : (a_0 * base_pow(0) + a_1 * base_pow(1) + ....... + a_k-1 * base_pow(k-1)) mod m
         *
         * Recursion : P(k+1) = P(k) + a_k * base_pow(k) mod m
         *
         * Here, for convenience, prefix[i] means hash of prefix with i characters, prefix[0] = 0;
         */

        long[] prefix = new long[n+1];
        for (int i = 0; i < n; i++) {
            prefix[i+1] = prefix[i] + ((target.charAt(i) - 'a' + 1) * base[i]) % mod;
        }

        /*
         * Hash of the sequence : (a_0 * base_pow(0) + ....... + a_n-1 * base_pow(n-1)) mod m
         *
         * Computing hash of the substring : from i to j.
         * 1. One way is to calculate the multiplicative inverse, and multiply both sides.
         * 2. Second, is this approach make both sides multiplied by same power of base,
         * and then compare both the hashes. (check the reference.)
         *
         * Multiply both sides by maXPower - i - len + 1. Or with maXPower - i - len - 1.
         * 1. '-i' is important to remove the i dependency.
         * 2. If we do for every length separately, len dependency is also not required.
         * 3. Left with maXPower - i + 1. Or maXPower - i - 1.
         */

        int count = 0;
        for (int l = 1; l <= n; l++) {
            Set<Long> set = new HashSet<>();
            for (int j = 0; j <= n - l; j++) {
                long current_hash = (prefix[j+l] - prefix[j] + mod) % mod;
                current_hash = (current_hash * base[n-j-1]) % mod;
                set.add(current_hash);
            }
            count = count + set.size();
        }

        return count;
    }

    public static void main(String[] args) {

        final StringHashing stringHashing = new StringHashing();
        System.out.println(stringHashing.computeHash("ankita"));

        final List<List<Integer>> groups = stringHashing.groupIdenticalStrings(Arrays.asList("varun", "ankita", "ankita", "varun"));
        System.out.println(groups);

        {
            final int count = stringHashing.countUniqueSubstring("aaa");
            System.out.println(count);
        }

        {
            final int count = stringHashing.countUniqueSubstring("aba");
            System.out.println(count);
        }
    }
}
