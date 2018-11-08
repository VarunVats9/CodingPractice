package com.practice.geeksforgeeks.dp.basic;

/**
 * Created by vvats on 08/11/18.
 */
public class LCS {


    private static void lcs(final String first, final String second) {

        int n = first.length();
        int m = second.length();

        /*
         * dp[i][j], it means calculated lcs for characters 1 to i in first and
         * 1 to j in second.
         *
         * Keeping dp[0][j] and dp[i][0] as 0, as there is no characters in the other one.
         */
        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                /*
                 * First, please create a small table between two strings, abc and ac. (lcs(abc, ac)).
                 * Now in this table, check what will happen if we are at c in both strings, and it matches.
                 * Then we need to find the lcs(ab, a) + 1(c == c matches). So, basically in the table the diagonal cell.
                 *
                 * Why we are not considered the upper and left cell. Because here we are saying that last char of both strings
                 * have matched, and we are left with strings with one length less in both the cases to do matching.
                 * i.e. (ab and a)
                 */
                if (first.charAt(i-1) == second.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                /*
                 * Now in this table, check what will happen if we are at c in second string, and b in first (lcs(ab, ac)).
                 * Since it doesn't match, find the lcs(ab, a) and lcs(a, ac), and return the max of these two.
                 * Basically in the table, the upper and left cell.
                 *
                 *
                 * Why we haven't considered the case of diagonal, since in going to diagonal we have to remove both the
                 * current target characters i.e. c and b, which is nothing but matching a and a. Whereas if we include
                 * the other unmatched characters,
                 * 1. It itself increases the chances of more matches, as we have string of more length. (ab and a & a and ac).
                 * 2. It(upper and left) already includes the diagonal cells.
                 *
                 */
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println("Longest common sub-sequence is : " + dp[n][m]);

    }


    public static void main(String[] args) {

        {
            LCS.lcs("AGGTAB", "GXTXAYB");
        }

    }



}
