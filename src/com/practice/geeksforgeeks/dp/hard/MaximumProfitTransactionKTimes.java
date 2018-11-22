package com.practice.geeksforgeeks.dp.hard;

/**
 * Date : 19 Nov, 2018
 * Time : 11:31 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class MaximumProfitTransactionKTimes {

    private static void profit(final int[] price, final int K) {

        int n = price.length;
        int m = K;

        /*
         * dp[i][j], it stores the maximum profit that can be calculated by taking transactions
         * from 1 to i, number of times. And, the trading days from 1 to j.
         */
        int[][] dp = new int[m+1][n+1];

        /*
         * O(n^3) solution.
         *
         * It solves by the following rule :
         *
         * 1. At any given day, either we don't do any transaction, i.e. ==> dp[i][j-1]
         * 2. Or considering the point that on the current day (i.e. j) we are selling the stock
         * which was bought on any day before that (i.e k which is from 1 to j-1) and add this value to the
         * dp[i-1][k] which is profit which accumulated till that.
         * ==> price[j-1] - price[k-1] + dp[i-1][k]
         *
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j-1], price[j-1] - price[k-1] + dp[i-1][k]));
                }
            }
        }
        */

        /*
         * O(n^2) solution.
         *
         * Now we can reduce this logic : price[j-1] - price[k-1] + dp[i-1][k] from 1 <= k < j
         *
         * Let's take an example to calculate j = 3, i = 2 :
         *
         * k = 0, dp[1][0] - price[0] + price[3]
         * k = 1, dp[1][1] - price[1] + price[3]
         * k = 2, dp[1][2] - price[2] + price[3]
         *
         * If you the above code, what we are doing here, we just calculate the maximum of k = 0, 1, 2 and
         * get the answer for dp[2][3]. To calculate the maximum, there is one common thing which we can take out,
         * i.e. price[3] and just calculate the maximum of (dp[i-1][k] - price[k-1]).
         *
         * For calculating the value for j = 4, i = 2 :
         *
         * k = 0, dp[1][0] - price[0] + price[4]
         * k = 1, dp[1][1] - price[1] + price[4]
         * k = 2, dp[1][2] - price[2] + price[4]
         * k = 3, dp[1][3] - price[3] + price[4]
         *
         * So, basically here also we again calculated the maximum of (first three (already calculated above), and the last one).
         * Excluding the price[4].
         *
         * So, recurrence rule becomes.
         *
         * prevDiff = Math.max(prevDiff, dp[i-1][j-1] - price[j-1]).
         * dp[i][j] = Math.max(dp[i][j-1], prevDiff).
         */

        for (int i = 1; i <= m; i++) {
            int prevDiff = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                prevDiff = Math.max(prevDiff, dp[i-1][j-1] - price[j-1]);
                dp[i][j] = Math.max(dp[i][j-1], price[j] + prevDiff);
            }
        }

        System.out.println("Maximum profit that can be earned is : " + dp[m][n-1]);
    }

    public static void main(String[] args) {

        {
            MaximumProfitTransactionKTimes.profit(new int[]{10, 22, 5, 75, 65, 80}, 2);
        }

        {
            MaximumProfitTransactionKTimes.profit(new int[]{12, 14, 17, 10, 14, 13, 12, 15}, 3);
        }

        {
            MaximumProfitTransactionKTimes.profit(new int[]{100, 30, 15, 10, 8, 25, 80}, 3);
        }

        {
            MaximumProfitTransactionKTimes.profit(new int[]{90, 80, 70, 60, 50}, 1);
        }
    }

}
