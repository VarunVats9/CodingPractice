package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class EditDistance {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String x = in.nextLine();
        String y = in.nextLine();
        int n = x.length(), m = y.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) dp[i][0] = i;
        for (int j = 1; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        out.println(dp[n][m]);
    }
}
