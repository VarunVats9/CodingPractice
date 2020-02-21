package com.practice.atcoder.educationaldp;

import java.util.Scanner;
import java.io.PrintWriter;

public class DKnapsack1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int W = in.nextInt();

        int[] w = new int[n + 1];
        int[] v = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        long[][] dp = new long[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (j < w[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
            }
        }

        out.println(dp[n][W]);
    }
}
