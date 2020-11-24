package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class KStones {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = in.nextIntArray(n);
        int[] dp = new int[100005];

        // Values [0 -> Losing state, 1 -> Winning state]
        dp[0] = 0;

        for (int i = 1; i <= k; i++) {
            dp[i] = 0;
            for (int j = 0; j < n; j++) {
                if (i >= arr[j]  && dp[i - arr[j]] == 0) {
                    dp[i] = 1;
                    break;
                }
            }
        }

        out.println(dp[k] == 1 ? "First" : "Second");

    }
}
