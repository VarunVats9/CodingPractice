package com.practice.chelper;

import java.util.Scanner;
import java.io.PrintWriter;

public class EKnapsack2 {

    private static int max = 100000;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int W = in.nextInt();

        int[] w = new int[n+1];
        int[] v = new int[n+1];

        for (int i = 1; i <= n; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        // dp[i][j] -> This holds the maximum weight achieved by using
        // the 'i' items till now, and 'j' value.
        long[][] dp = new long[n+1][max+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= max; j++) {
                if (dp[i-1][j] + w[i] < W) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], w[i] + dp[i-1][j-v[i]]);
            }
        }

        out.println(dp[n][max]);
    }
}
