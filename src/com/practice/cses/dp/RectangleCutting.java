package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class RectangleCutting {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt(), b = in.nextInt();
        int[][] dp = new int[a+1][b+1];

        for (int i = 0; i <= a; i++)
            Arrays.fill(dp[i], 1_000_000_007);

        dp[0][0] = 0;

        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }
                // Horizontal Cuts
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i][k] + dp[i][j - k]);
                }
                // Vertical Cuts
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[k][j] + dp[i - k][j]);
                }
            }
        }

        out.println(dp[a][b]);
    }
}
