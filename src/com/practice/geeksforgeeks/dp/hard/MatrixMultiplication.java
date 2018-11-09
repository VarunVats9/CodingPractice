package com.practice.geeksforgeeks.dp.hard;

/**
 * Created by vvats on 09/11/18.
 */
public class MatrixMultiplication {

    private static void matrixMul(final int[] array) {

        int n = array.length;

        // dp[i][j], it means minimum matrix multiplication from i to j.
        int[][] dp = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            dp[i][i] = 0;
        }

        // Length from i to j.
        for (int l = 1; l <= n-1; l++) {

            for (int i = 1; i <= n-l; i++) {
                /*
                 * For length l = 1, loop would go from
                 * i = 1,    (1, j = 2)
                 * i = 2,    (2, j = 3)
                 * i = n-l   (n-l, j = n)
                 * and for last element, i = n-l-1+l
                 */
                int j = i + l;

                /*
                 * For length = 1, dp[i][j] = 0, why what is the matrix multiplication for
                 * 10 * 20 matrix, it is zero. Already calculated.
                 */
                if (l == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                if (l == 2) {
                    dp[i][j] = array[i-1] * array[i] * array[i+1];
                    continue;
                }

                /*
                 * Let's take an example, suppose for M1 * M2 * M3 * .... * Mn.
                 *
                 * Min ( M1.(M2, M3, ... Mn), (M1, M2, ... Mn-1).Mn)
                 *
                 * M1 = array[0] * array[1]
                 * (M2, M3, ... Mn) = dp[2][n]
                 *
                 * M1.(M2, M3, ... Mn) = array[0] * array[1] * array[n-1] + dp[2][n].
                 * (M1, M2, ... Mn-1).Mn = dp[1][n-1] + array[0] * array[n-1] * array[n-2].
                 *
                 */
                dp[i][j] = Math.min((array[i-1] * array[i] * array[j-1]) + dp[i+1][j],
                        dp[i][j-1] + (array[i-1] * array[j-1] * array[j-2]));
            }
        }

        System.out.println("Minimum matrix multiplication required: " + dp[1][n]);
    }

    public static void main(String[] args) {

        {
            MatrixMultiplication.matrixMul(new int[]{40, 20, 30, 10, 30});
        }

        {
            MatrixMultiplication.matrixMul(new int[]{10, 20, 30, 40, 30});
        }

        {
            MatrixMultiplication.matrixMul(new int[]{10, 20, 30});
        }

    }


}
