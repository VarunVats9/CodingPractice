package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class MCandies {

    static int MOD = (int) (1e9+7);


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = in.nextIntArray(n);
        int[][] dp = new int[n+1][k+1];

        for (int i = 0; i <= arr[0]; i++) {
            dp[0][i] = 1;
        }

        int[] ps = new int[k+1];

        for (int i = 1; i < n; i++) {
            // prefix sum;
            ps[0] = dp[i-1][0];
            for (int p = 1; p <= k; p++) {
                ps[p] = (dp[i-1][p] + ps[p-1]) % MOD;
            }

            for (int j = 0; j <= k; j++) {
                dp[i][j] = j-arr[i] > 0 ? (ps[j] + MOD - ps[j-arr[i]-1]) % MOD : ps[j];
            }

        }

        out.println(dp[n-1][k]);
    }
}
