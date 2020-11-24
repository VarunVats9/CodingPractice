package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class ArrayDescription {

    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] dp = new int[n+1][m+2];
        int[] arr = in.nextIntArray(n);

        for (int j = 1; j <= m; j++) {
            if (arr[0] == 0 || arr[0] == j)
                dp[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i-1] == 0 || arr[i-1] == j) {
                    dp[i][j] = mod(dp[i-1][j-1], mod(dp[i-1][j], dp[i-1][j+1]));
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= m; i++)
            ans = mod(ans, dp[n][i]);
        out.println(ans);
    }

    private int mod(int a, int b) {
        return (a % MOD + b % MOD) % MOD;
    }
}
