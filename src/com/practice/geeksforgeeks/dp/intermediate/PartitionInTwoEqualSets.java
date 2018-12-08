package com.practice.geeksforgeeks.dp.intermediate;

import java.util.ArrayList;
import java.util.List;

/**
 * Date : 07 Nov, 2018
 * Time : 8:58 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class PartitionInTwoEqualSets {

    /*
     * This is a modified version of Knapsack.
     * Here the weight and values are same, and the limit has to be equal to the sum/2.
     *
     * In knapsack problem, the limit tells the maximum weight you have in the knapsack, which can be
     * achieved, by putting the items in the knapsack, to create weight <= limit. (and which maximizes the value).
     *
     * The last step, dp[n][m/2] == m/2, it checks whether we were able to achieve the limit (sum/2).
     */
    private static boolean partitionInteger(final int[] array) {

        int n = array.length;
        int m = 0;

        for (int i = 0; i < n; i++) {
            m += array[i];
        }

        if (m % 2 != 0) {
            return false;
        }

        int[][] dp = new int[n+1][m/2+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m/2; j++) {

                if (i < 1 || j < 1) {
                    continue;
                }

                if (array[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], array[i-1] + dp[i-1][j - array[i-1]]);
                    continue;
                }

                dp[i][j] = dp[i-1][j];
            }
        }

        return dp[n][m/2] == m/2;
    }

    private static boolean partitionBoolean(final int[] array) {

        int n = array.length;
        int m = 0;

        for (int i = 0; i < n; i++) {
            m += array[i];
        }

        if (m % 2 != 0) {
            return false;
        }

        /*
         * dp[i][j], it means all the items from 1 to i, makes the sum as j.
         */
        boolean[][] dp = new boolean[n+1][m/2+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m/2; j++) {

                // None items cannot create any sum.
                if (i == 0) {
                    dp[i][j] = false;

                    //(0, 0) : it overrides the previous false, and should be true. Because none item can create 0 as sum.
                    if (j == 0) {
                        dp[i][j] = true;
                    }
                    continue;
                }

                /*
                 * All items can create 0, as sum by not getting selected.
                 */
                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (array[i-1] <= j) {
                    /*
                     * Either the item gets excluded or included.
                     * If excluded, then check the result from 1 to i-1, else check by decreasing the sum.
                     */
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - array[i-1]];
                    continue;
                }

                dp[i][j] = dp[i-1][j];
            }
        }

        /*
         * Printing partition.
         * In this start from the last cell, if the above value is False and the current is True, that means,
         * the current item has been included, and remove the weight of current item from
         * the column value of the cell.
         */

        System.out.print("Items to be included in partitions : ");
        List<Integer> set1 = new ArrayList<>(), set2 = new ArrayList<>();
        int col = m/2;
        for (int i = n; i > 0 && col >= 0; i--) {

            /*
             * It means, we have excluded the current item.
             * Because current value = Exclude Or Include, if exclude is True, we don't check Include.
             * and exclude True means, we have to exclude current item.
             */
            if (dp[i-1][col]) {
                set2.add(array[i-1]);
            }

            /*
             * Else means, exclude is False, and if include comes out to be True. We have to include current item.
             */
            else if (dp[i-1][col - array[i-1]]) {
                set1.add(array[i-1]);
                col = col - array[i-1];
            }
        }

        System.out.println("Set 1 : " + set1);
        System.out.println("Set 2 : " + set2);

        return dp[n][m/2];
    }

    public static void main(String[] args) {

        {
            System.out.println(PartitionInTwoEqualSets.partitionInteger(new int[]{3, 1, 1, 2, 2, 5}));
        }

        {
            System.out.println(PartitionInTwoEqualSets.partitionBoolean(new int[]{3, 1, 1, 2, 2, 5}));
        }

    }

}
