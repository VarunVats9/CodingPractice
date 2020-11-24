package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class __TLE__CoinCombinationsII {

    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt(), n = in.nextInt();
        int[] arr = new int[m];
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m; i++) arr[i] = in.nextInt();
        for (int idx = 0; idx <= m; idx++) dp[idx][0] = 1;

        for (int idx = 1; idx <= m; idx++) {
            for (int rem = 1; rem <= n; rem++) {
                dp[idx][rem] = dp[idx-1][rem];
                if (rem-arr[idx-1] < 0) continue;
                dp[idx][rem] = (dp[idx][rem] + dp[idx][rem-arr[idx-1]]) % MOD;
            }
        }
        out.println(dp[m][n]);
    }
}
