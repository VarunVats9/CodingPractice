package com.practice.geeksforgeeks.dp.hard;

/**
 * Date : 17 Nov, 2018
 * Time : 5:56 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class MaximumSumRectangle {

    // Col : x1, x2 and Row : y1, y2 (Enclosing a rectangle).
    private static int x1, x2, y1, y2,
            maxX1, maxX2, maxY1, maxY2;

    private static void maxRectangle(final int[][] matrix) {

        int r = matrix.length;
        int c = matrix[0].length;
        int maxSum = 0;

        for (x1 = 0; x1 < c; x1++) {
            // dp[i], it stores the values till column 'i'.
            int[] dp = new int[r];
            for (x2 = x1; x2 < c; x2++) {
                for (int i = 0; i < r; i++) {
                    dp[i] = dp[i] + matrix[i][x2];
                }
                // Apply Kadane's algorithm, to calculate y1, and y2.
                int tempSum = kadane(dp);
                if (maxSum < tempSum) {
                    maxX1 = x1; maxX2 = x2;
                    maxY1 = y1; maxY2 = y2;
                    maxSum = tempSum;
                }
            }
        }

        System.out.println("Maximum rectangle sum : " + maxSum + " with (top, left) : " + maxY1 + ", " + maxX1
                            + " and (bottom, right) : " + maxY2 + ", " + maxX2);
    }

    private static int kadane(final int[] dp) {

        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        y1 = y2 = 0;

        for (int i = 0; i < dp.length; i++) {
            sum = sum + dp[i];
            if (sum < 0) {
                y1 = i + 1;
                sum = 0;
                continue;
            }

            if (maxSum < sum) {
                maxSum = sum;
                y2 = i;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        {
            System.out.println(MaximumSumRectangle.kadane(new int[]{1, 3, -4, 9, -1, 3}));
        }

        {
            MaximumSumRectangle.maxRectangle(new int[][]{{1, 2, -1, -4, -20},
                                    {-8, -3, 4, 2, 1}, {3, 8, 10, 1, 3}, {-4, -1, 1, 7, -6}});
        }

    }

}
