package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TwoSetsII {

    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int sum = (n * (n+1)) / 2;
        int ans = 0;
        if (sum % 2 != 0) {
            out.println(ans);
            return;
        }

        sum = sum / 2;
        long[] dp = new long[sum+1];

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= i; j--) {
                dp[j] = (dp[j-i] + dp[j]) % MOD;
            }
        }

        out.println((dp[sum] * (MOD+1)/2) % MOD);
    }
}
