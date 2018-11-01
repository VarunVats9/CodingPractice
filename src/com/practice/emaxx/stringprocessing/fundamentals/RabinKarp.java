package com.practice.emaxx.stringprocessing.fundamentals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vvats on 31/10/18.
 */
public class RabinKarp {


    public List<Integer> countOccurrenceOfPatternInText(final String pattern, final String text) {

        int prime = 31;
        long mod = 100_000_009L;
        long patternHash = 0;
        int n = pattern.length();
        int m = text.length();

        long[] base = new long[m+1];
        base[0] = 1;

        // Calculate base power.
        for (int i = 1; i <= m; i++) {
            base[i] = (base[i-1] * prime) % mod;
        }

        // Calculate the whole hash of the pattern.
        for (int i = 0; i < n; i++) {
            patternHash = patternHash + ((pattern.charAt(i) - 'a' + 1) * base[i]) % mod;
        }

        long[] prefix = new long[m+1];

        // Here, for convenience, prefix[i] means hash of prefix with i characters, prefix[0] = 0;
        for (int j = 0; j < m; j++) {
            prefix[j+1] = prefix[j] + ((text.charAt(j) - 'a' + 1) * base[j]) % mod;
        }

        final List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= m - n; i++) {
            long currentHash = (prefix[i+n] - prefix[i] + mod) % mod;
            if (currentHash == (patternHash * base[i]) % mod) {
                result.add(i);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        final RabinKarp rabinKarp = new RabinKarp();
        System.out.println(rabinKarp.countOccurrenceOfPatternInText("va", "varunvats"));
    }

}
