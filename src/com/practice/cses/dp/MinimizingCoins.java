package com.practice.cses.dp;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class MinimizingCoins {

    static int MAX = (int) 1e8;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] arr = in.nextIntArray(n);

        int[][] dp = new int[n+1][x+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                if (j-arr[i-1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(1 + dp[i][j - arr[i - 1]], dp[i - 1][j]);
                }
            }
        }

        out.println(dp[n][x] == MAX ? -1 : dp[n][x]);
    }
}
