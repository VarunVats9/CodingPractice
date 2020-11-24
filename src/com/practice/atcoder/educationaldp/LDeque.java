package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class LDeque {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }

        long[][][] dp = new long[3001][3001][2];

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i+k-1 <= n; i++) {
                int j = i+k-1;
                if (k == 1) {
                    dp[i][j][0] = arr[i-1];
                    dp[i][j][1] = 0;
                    continue;
                }
                dp[i][j][0] = Math.max(arr[j-1] + dp[i][j-1][1], arr[i-1] + dp[i+1][j][1]);
                dp[i][j][1] = Math.min(dp[i][j-1][0], dp[i+1][j][0]);
            }
        }

        out.println(2 * dp[1][n][0] - sum);

    }
}
