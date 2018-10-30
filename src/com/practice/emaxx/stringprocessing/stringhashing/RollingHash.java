package com.practice.emaxx.stringprocessing.stringhashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public static void main(String[] args) {

        final RollingHash rollingHash = new RollingHash();
        System.out.println(rollingHash.computeHash("ankita"));

        final List<List<Integer>> groups = rollingHash.groupIdenticalStrings(Arrays.asList("varun", "ankita", "ankita", "varun"));
        System.out.println(groups);
    }
}
