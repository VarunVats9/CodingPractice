package com.practice.geeksforgeeks.dp.intermediate;

/**
 * Binomial Coefficient Recursion, is based on Pascal's rule :
 * Suppose, you need to make a team of 10 people, out of 100. Then it will be C(100, 10).
 * Now, if there is one guy, who is really bad (yourself),
 * and if he/she is there on the team, then that can also become bad.
 * So, now there is possibility of either BAD or GOOD teams.
 * How to make a BAD team : If you are there on the team, then you need to find 9 other guys from 99. C(99, 9)
 * How to make a GOOD team : Excluding you (keeping you aside), then you need to find 10 other guys from 99. C(99, 10)
 * C(100, 10) = C(99, 9) + C(99, 10)
 * C(n, k) = C(n-1, k-1) + C(n-1, k)
 */
public class BinomialCoefficientRecursion {


    public static long binomialCoefficient(final int n, final int k) {

        long[][] dp = new long[n+1][k+1];

        /*
         * Since C(100, 90) == C(100, 10), no need to calculate till dp[100][90].
         * Just the dp[100][10] is enough.
         */
        int optimizedK = Math.min(n-k, k);

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= optimizedK; j++) {

                if (j > i) {
                    continue;
                }

                if(j == 0 || j == i) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        return dp[n][optimizedK];
    }



    public static void main(String[] args) {

        {
            System.out.println(BinomialCoefficientRecursion.binomialCoefficient(100, 90));
        }

        {
            System.out.println(BinomialCoefficientRecursion.binomialCoefficient(3, 2));
        }

        {
            System.out.println(BinomialCoefficientRecursion.binomialCoefficient(3, 1));
        }
    }
}
