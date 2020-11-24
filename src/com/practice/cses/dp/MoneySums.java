package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MoneySums {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }

        boolean[][] dp = new boolean[n+1][sum+1];

        for (int i = 0; i <= n; i++) dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j-arr[i-1] < 0) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
            }
        }

        int count = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[j][i]) {
                    count++;
                    res.add(i);
                    break;
                }
            }
        }

        out.println(count);
        for (int i = 0; i < res.size(); i++) {
            out.print(res.get(i) + " ");
        }
        out.println();
    }
}
