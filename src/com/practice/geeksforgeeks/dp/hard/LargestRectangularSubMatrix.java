package com.practice.geeksforgeeks.dp.hard;

import java.util.HashMap;

import com.practice.geeksforgeeks.array.LongestSubarraySumK;

/**
 * Date : 17 Nov, 2018
 * Time : 5:43 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class LargestRectangularSubMatrix {

    // Col : x1, x2 and Row : LongestSubarraySumK.y1, LongestSubarraySumK.y2 (Enclosing a rectangle).
    private static int x1, x2, maxX1, maxX2, maxY1, maxY2;

    private static void maxRectangle(final int[][] matrix) {

        int r = matrix.length;
        int c = matrix[0].length;
        int maxRectArea = 0;

        for (x1 = 0; x1 < c; x1++) {
            // dp[i], it stores the values till column 'i'.
            int[] dp = new int[r];
            for (x2 = x1; x2 < c; x2++) {
                for (int i = 0; i < r; i++) {
                    dp[i] = dp[i] + matrix[i][x2];
                }
                // Apply modified Kadane's algorithm, to calculate y1, and y2.
                if (LongestSubarraySumK.longestSumK(dp, 0) && (maxRectArea < (x2 - x1)*(LongestSubarraySumK.y2 - LongestSubarraySumK.y1))) {
                    maxX1 = x1; maxX2 = x2;
                    maxY1 = LongestSubarraySumK.y1; maxY2 = LongestSubarraySumK.y2;
                    maxRectArea = (x2 - x1) * (LongestSubarraySumK.y2 - LongestSubarraySumK.y1);
                }
            }
        }

        System.out.println("Maximum rectangle with sum as 0, " + " with (top, left) : " + maxY1 + ", " + maxX1
                + " and (bottom, right) : " + maxY2 + ", " + maxX2);
    }



    public static void main(String[] args) {

        {
            LargestRectangularSubMatrix.maxRectangle(new int[][]{{9, 7, 16, 5}, {1, -6, -7, 3}, {1, 8, 7, 9}, {7, -2, 0, 10}});
        }

    }

}
