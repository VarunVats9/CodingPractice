package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class __TLE__CoinCombinationsI {
    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt(), n = in.nextInt();
        int[] arr = new int[m];
        long[] dp = new long[n+1];
        dp[0] = 1;
        for (int i = 0; i < m; i++) arr[i] = in.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (i-arr[j] < 0) continue;
                dp[i] = (dp[i] + dp[i-arr[j]]) % MOD;
            }
        }
        out.println(dp[n]);
    }
}
