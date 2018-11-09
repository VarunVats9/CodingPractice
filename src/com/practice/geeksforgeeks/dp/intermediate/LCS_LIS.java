package com.practice.geeksforgeeks.dp.intermediate;

/**
 * Reference : https://blog.codechef.com/2009/05/19/211/
 * Reference : https://qr.ae/TUhuiD
 */
public class LCS_LIS {

    private static void lcsLis(final int[] first, final int[] second) {

        int n = first.length;
        int m = second.length;

        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            /*
             * Variable saving the maximum potential lcis, calculated so far, we are going from left to right.
             */
            int theta = 0;

            for (int j = 1; j <= m; j++) {

                /*
                 * According to reference link, for every cell dp[i][j], we can increase the already calculated maximum lcis
                 * from dp[1][1] to dp[i-1][j-1], basically in that matrix, so the time complexity is O(n^4).
                 *
                 * 1. Keeping in mind that the current cell value second[j-1] has to be greater than the maximum potential lcis cell value. (It takes care of LIS)
                 * 2. Both the first[i-1] == second[j-1]. (It takes care of LCS)
                 *
                 * Now, we can take this complexity down to O(n^3) by instead of matrix, we can have an array, which has the maximum lcis
                 * calculated till col (1 to j-1).
                 *
                 * We take this down to O(n^2) by having an variable saving the maximum lcis.
                 */

                // dp[i][j], it means the lcis, calculated for first array from 1 to i and second array from 1 to j.
                dp[i][j] = dp[i-1][j];

                // Considering LIS, theta starts from 0, and for every potential lcis, we calculate the maximum lcis.
                if (first[i-1] > second[j-1]) {
                    theta = Math.max(theta, dp[i][j]);
                }

                // Considering LCS, found the common element, we can update the theta for the dp[i][j].
                if (first[i-1] == second[j-1]) {
                    dp[i][j] = theta + 1;
                }
            }
        }

        int lcis = 0;

        // Traversing the last row, to get the maximum lcis.
        for (int j = 1; j <= m; j++) {
            lcis = Math.max(lcis, dp[n][j]);
        }

        System.out.println("Longest common increasing sub-sequence is : " + lcis);

    }

    public static void main(String[] args) {

        {
            LCS_LIS.lcsLis(new int[]{3, 4, 9, 1}, new int[]{5, 3, 8, 9, 10, 2, 1});
        }

        {
            LCS_LIS.lcsLis(new int[]{4, 3, 5, 6, 7, 1, 2}, new int[]{1, 2, 3, 50, 6, 4, 7});
        }

        {
            LCS_LIS.lcsLis(new int[]{8, 3, 9, 4, 5}, new int[]{8, 9, 3, 4, 5});
        }
    }



}
