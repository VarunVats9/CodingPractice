package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BookShop {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int b = in.nextInt(), mp = in.nextInt();
        int[] br = new int[b+1];
        int[] bg = new int[b+1];
        for (int i = 1; i <= b; i++) {
            br[i] = in.nextInt();
        }
        for (int i = 1; i <= b; i++) {
            bg[i] = in.nextInt();
        }
        int[][] dp = new int[b+1][mp+1];

        for (int i = 1; i <= b; i++) {
            for (int j = 1; j <= mp; j++) {
                dp[i][j] = dp[i-1][j];
                if ((j - br[i]) < 0) continue;
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j-1], bg[i] + dp[i-1][j-br[i]]));
            }
        }

        out.println(dp[b][mp]);
    }
}
