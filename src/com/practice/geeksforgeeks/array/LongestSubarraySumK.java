package com.practice.geeksforgeeks.array;

import java.util.HashMap;

import com.practice.geeksforgeeks.dp.hard.LargestRectangularSubMatrix;

/**
 * Date : 18 Nov, 2018
 * Time : 11:42 AM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class LongestSubarraySumK {

    public static int y1, y2;

    public static boolean longestSumK(final int[] dp, final int K) {

        /*
         * This modified kadane is based on the fact, that
         * sum (till current idx) - sum (till previous idx) = K
         * Here, K = 0.
         *
         * Difference between both the indices gives you the length.
         */

        HashMap<Integer, Integer> sumToIndex = new HashMap<>();
        /*
         * Why we have added -1 to the index ?
         * Because, it takes care of the fact that all y2 indices
         * have +1 added to it. So, thinking of one of the cases, where the y1 = y2 = 0,
         * it helps in that.
         */
        sumToIndex.put(0, -1);

        int sumTillCurrIdx = 0;
        int maxLength = 0;

        for (int i = 0; i < dp.length; i++) {
            sumTillCurrIdx = sumTillCurrIdx + dp[i];

            if (!sumToIndex.containsKey(sumTillCurrIdx)) {
                sumToIndex.put(sumTillCurrIdx, i);
            }

            if (sumToIndex.containsKey(sumTillCurrIdx - K)) {
                int diff = i - sumToIndex.get(sumTillCurrIdx - K);
                if (diff > maxLength) {
                    maxLength = diff;
                    y2 = i;
                    /*
                     * Why we have added 1 to the index ?
                     * Because, when we do, current idx - previous idx, actually the sum started
                     * from previous idx + 1.
                     */
                    y1 = sumToIndex.get(sumTillCurrIdx - K) + 1;
                }
            }
        }

        System.out.println("Y1 : " + y1 + ", Y2 : " + y2);
        return maxLength != 0;
    }

    public static void main(String[] args) {

        {
            System.out.println(LongestSubarraySumK.longestSumK(new int[]{23, 1, -24}, 0));
        }

        {
            System.out.println(LongestSubarraySumK.longestSumK(new int[]{-1, 1, 0}, 0));
        }

        {
            System.out.println(LongestSubarraySumK.longestSumK(new int[]{5, 0, -1, 1, 0}, 0));
        }

        {
            System.out.println(LongestSubarraySumK.longestSumK(new int[]{-5, 1, 4}, 0));
        }

    }


}
