package com.practice.atcoder.educationaldp;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class BFrog2 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n+1];

        // dp[i] -> The minimum cost to reach here.
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
        }

        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i <= j) continue;
                dp[i] = Math.min(dp[i], dp[i-j] + Math.abs(arr[i] - arr[i-j]));
            }
        }

        out.println(dp[n]);
    }
}
