package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class RemovingDigits {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            int min = Integer.MAX_VALUE;
            while (num > 0) {
                int rem = num % 10;
                if (rem > 0) {
                    min = Math.min(min, 1 + dp[i - rem]);
                }
                num /= 10;
            }
            dp[i] = min;
        }

        out.println(dp[n]);
    }
}
