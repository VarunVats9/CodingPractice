package com.practice.geeksforgeeks.dp.intermediate;

/**
 * Created by vvats on 15/11/18.
 */
public class LongestPalindromicSubsequence {

    private static void lps(final String target) {

        /*
         * One easy solution is, LCS(target, reverseTarget).
         */

        int n = target.length();

        // dp[i][j], it means that the maximum lps calculated from i to j position.
        int[][] dp = new int[n+1][n+1];

        // All single character are of length 1 lps.
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        for (int l = 1; l < n; l++) {
            /*
             * l would go from 1 to n-1, and i would go from 1 to n-l
             * and j = i + l.
             */
            for (int i = 1; i <= n-l; i++) {
                int j = i+l;
                /*
                 * If the first(i) and the last(j) char matches for the target, that would mean that
                 * 2 have matched, and the answer would be 2 + (already calculated lps from second to second last).
                 *
                 * And if, j is next to i, there won't be any smaller lps.
                 */
                if (target.charAt(i-1) == target.charAt(j-1)) {
                    if (j == i+1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                }

                /*
                 * Else, just the maximum of (lps(second, last) OR lps(first, second last)).
                 *
                 * If someone thinks, we could have added, (third value also above, like lps(second, second last)),
                 * then that is wrong, since
                 *
                 * 1. It is a smaller target than other two.
                 * 2. It is already been calculated in the another two anyways.
                 */
                else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }

        System.out.println("Maximum length for lps is : " + dp[1][n]);
    }

    public static void main(String[] args) {

        {
            LongestPalindromicSubsequence.lps("GEEKSFORGEEKS");
        }

        {
            LongestPalindromicSubsequence.lps("BACB");
        }

    }

}
