package com.practice.cses.dp;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;

public class RemovalGame {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.nextIntArray(n);
        long[][] dp = new long[n+1][n+1];

        long sum = 0;
        for (int i = 0; i < n; i++) sum += arr[i];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = arr[i-1];
        }

        for (int k = 2; k <= n; k++) {
            for (int i = 1; i+k-1 <= n; i++) {
                int j = i+k-1;
                dp[i][j] = Math.max(arr[j-1] - dp[i][j-1], arr[i-1] - dp[i+1][j]);
            }
        }

        out.println((sum + dp[1][n]) / 2);
    }
}
