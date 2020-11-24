package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class EKnapsack2 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int W = in.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
            sum += v[i];
        }

        int max = Math.min((int) 1e5, sum);
        int wmax = (int) 1e9 + 3;

        int[][] dp = new int[n+1][max+1];

        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], wmax);
        for (int i = 0; i <= n; i++) dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= max; j++) {
                dp[i][j] = dp[i-1][j];
                if (j-v[i-1] >= 0)
                    dp[i][j] = Math.min(dp[i-1][j], w[i-1] + dp[i-1][j-v[i-1]]);
                if (dp[i][j] > 1e9) {
                    dp[i][j] = wmax;
                }
            }
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= max; j++) {
                if (dp[i][j] <= W) {
                    ans = Math.max(ans, j);
                }
            }
        }

        out.println(ans);
    }
}
