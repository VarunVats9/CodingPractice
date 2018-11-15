package com.practice.geeksforgeeks.dp.basic;

/**
 * Created by vvats on 07/11/18.
 */
public class LIS {

    private static void lis(final int[] array) {

        int n = array.length;

        /*
         * dp[i], it means that till the element i, the LIS is dp[i].
         */
        int[] dp = new int[n+1];

        /*
         * Each element has LIS of length 1.
         */
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
        }

        int lis = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                if (array[j-1] > array[i-1] && dp[j] < dp[i] + 1) {
                    dp[j] = dp[i] + 1;
                    lis = Math.max(lis, dp[j]);
                }
            }
        }

        System.out.println("Longest Increasing Sequence has length : " + lis);

    }

    public static void main(String[] args) {

        {
            LIS.lis(new int[]{10, 22, 9, 33, 21, 50, 41, 60});
        }

        /*
         * 1. Building Bridges.
         * 2. Maximum Sum Increasing Sub-sequence.
         * 3. The Longest Chain.
         * 4. Box Stacking.
         * 5. Weighted Job Scheduling.
         */

        {
            //LIS.buildingBridges(); Check intermediate package.
        }

        {
            /*
             * Maximum Sum Increasing Sub-sequence.
             *
             * Q. Given an array of n positive integers. Write a program to find the maximum sum sub-sequence
             * of the given array such that the integers in the sub-sequence are sorted in increasing order.
             * For example, if input is {1, 101, 2, 3, 100, 4, 5}, then output should be {1, 2, 3, 100}.
             *
             * A. Since we cannot rearrange, like LIS. We will just use the modified LIS where we need to check
             * if (array[j-1] > array[i-1] && dp[j] < dp[i] + array[j-1]), here dp[i], saves the maximum sum till
             * the ith element.
             */
        }

        {
            /*
             * The Longest Chain
             *
             * Q. You are given pairs of numbers. In a pair, the first number is smaller with respect to
             * the second number. Suppose you have two sets (a, b) and (c, d), the second set can follow
             * the first set if b < c. So you can form a long chain in the similar fashion.
             * Find the longest chain which can be formed.
             *
             * A. Since we can rearrange, unlike LIS. First, we will sort the pairs, based on the first element.
             * Then we will just use the modified LIS where we need to check if (b > c && dp[j] < dp[i] + 1)
             */
        }

        {
            //LIS.boxStacking(); Check intermediate package.
        }

        {
            //LIS.weightedJobScheduling(); Check intermediate package.
        }

    }

}
