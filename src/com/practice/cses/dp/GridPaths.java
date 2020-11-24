package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class GridPaths {

    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[][] arr = new char[n+1][n+1];
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            String s = in.nextLine();
            for (int j = 1; j <= n; j++) {
                arr[i][j] = s.charAt(j-1);
            }
        }

        int value = 1;
        for (int j = 1; j <= n; j++) {
            if (arr[1][j] == '*') {
                value = 0;
            }
            dp[1][j] = value;
        }

        value = 1;
        for (int i = 1; i <= n; i++) {
            if (arr[i][1] == '*') {
                value = 0;
            }
            dp[i][1] = value;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                if (arr[i][j] == '*') {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 1 || j == 1) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i-1][j] % MOD + dp[i][j-1] % MOD) % MOD;
            }
        }

        out.println(dp[n][n]);
    }
}
