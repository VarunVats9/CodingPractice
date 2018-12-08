package com.practice.geeksforgeeks.dp.intermediate;

/**
 * Date : 06 Nov, 2018
 * Time : 2:58 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class Knapsack0_1_AndPrintItems {

    private static void knapSack(final int[] weight, final int[] values, final int limit) {

        int n = weight.length;

        // dp[i][j], it means that when using items from 1 to i, and limit is j.
        // Then what is the maximum value attained.
        int[][] dp = new int[n+1][limit+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= limit; j++) {

                // i == 0 means, when there is no item. And j == 0 means limit is 0.
                if (i < 1 || j < 1) {
                    continue;
                }

                if (weight[i-1] <= j) {
                    // Maximum is either include the current weight, or not include.
                    dp[i][j] = Math.max(values[i-1] + dp[i-1][j - weight[i-1]], dp[i-1][j]);
                    continue;
                }

                /*
                 * Weight of the item is more than limit.
                 * Hence, considering the case of items from 1 to i-1, and limit j.
                 */
                dp[i][j] = dp[i-1][j];
            }

        }

        /*
         * Printing knapsack.
         * In this start from the last cell, if the above value is same, that means,
         * the current item has not been included, else remove the weight of current item from
         * the column value of the cell.
         */

        System.out.print("Items to be included in knapsack : ");
        int col = limit;
        for (int i = n; i >= 1; i--) {
            if (dp[i][col] == dp[i-1][col]) {
                continue;
            }
            System.out.print(weight[i-1] + " ");
            col = col - weight[i-1];
        }

        System.out.println("\nMaximum value that can be achieved : " + dp[n][limit] + "\n");
    }

    private static void unboundedKnapSack(final int[] weight, final int[] values, final int limit) {

        int n = weight.length;

        int[] dp = new int[limit+1];

        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= n; j++) {
                if (i < 1 || j < 1) {
                    continue;
                }
                if (weight[j-1] <= i) {

                    /*
                     * dp[i], it means max value that limit i can achieve.
                     */
                    dp[i] = Math.max(dp[i], values[j - 1] + dp[i - weight[j - 1]]);
                }
            }
        }

        System.out.println("Maximum value that can be achieved in unbounded: " + dp[limit]);
    }

    public static void main(String[] args) {

        {
            Knapsack0_1_AndPrintItems.knapSack(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50);
        }

        {
            Knapsack0_1_AndPrintItems.knapSack(new int[]{2, 7, 5}, new int[]{2, 7, 5}, 6);
        }

        {
            Knapsack0_1_AndPrintItems.unboundedKnapSack(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 50);
        }

        {
            Knapsack0_1_AndPrintItems.unboundedKnapSack(new int[]{3, 8, 6}, new int[]{7, 8, 4}, 10);
        }

    }

}
