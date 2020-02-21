package com.practice.atcoder.educationaldp;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class AFrog1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n+1];

        // dp[i] -> The minimum cost to reach here.
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
        }

        dp[1] = 0;
        dp[2] = Math.abs(arr[2] - arr[1]);
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + Math.abs(arr[i] - arr[i-1]), dp[i-2] + Math.abs(arr[i] - arr[i-2]));
        }

        out.println(dp[n]);
    }
}
