package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;

public class ICoins {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        double[] p = new double[n];
        String s = in.nextLine();
        String[] tokens = s.split(" ");
        for (int i = 0; i < n; i++) {
            p[i] = Double.parseDouble(tokens[i]);
        }

        double[][] dp = new double[n+1][n+1];

        dp[0][0] = 1 - p[0];
        dp[0][1] = p[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                // tail
                dp[i][j] += (1 - p[i]) * dp[i - 1][j];
                // head
                if (j > 0)
                    dp[i][j] += (p[i]) * dp[i - 1][j - 1];
            }
        }

        double ans = 0;

        for (int i = n / 2 + 1; i <= n; i++) {
            ans += dp[n-1][i];
        }

        out.println(ans);

    }
}
