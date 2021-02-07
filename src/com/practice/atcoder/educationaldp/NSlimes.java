package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class NSlimes {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.nextIntArray(n);

        long[][] dp = new long[n+1][n+1];
        long[][] sum = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n; i++) {
                int j = i + l - 1;
                if (j <= n) {
                    if (l == 1) {
                        sum[i][j] = arr[i-1];
                        dp[i][j] = 0;
                        continue;
                    }
                    if (l == 2) {
                        sum[i][j] = arr[i - 1] + arr[j - 1];
                        dp[i][j] = sum[i][j];
                        continue;
                    }
                    sum[i][j] = arr[i-1] + sum[i+1][j];
                    for (int k = i + 1; k <= j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k][j] + sum[i][j]);
                    }
                }
            }
        }

        out.println(dp[1][n]);
    }
}
