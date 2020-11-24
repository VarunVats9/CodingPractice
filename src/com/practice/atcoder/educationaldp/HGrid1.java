package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;

public class HGrid1 {

    static int MOD = (int) (1e9+7);

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int h = in.nextInt();
        int w = in.nextInt();
        char[][] arr = new char[h][w];
        for (int i = 0; i < h; i++) {
            arr[i] = in.nextLine().toCharArray();
        }

        int[][] dp = new int[h+1][w+1];

        for (int i = 1; i <= h; i++) {
            if (arr[i-1][0] == '.') {
                dp[i][1] = 1;
            } else break;
        }

        for (int i = 1; i <= w; i++) {
            if (arr[0][i-1] == '.') {
                dp[1][i] = 1;
            } else break;
        }

        for (int i = 2; i <= h; i++) {
            for (int j = 2; j <= w; j++) {
                if (arr[i-1][j-1] == '.') {
                    dp[i][j] = mod(dp[i-1][j], dp[i][j-1]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        out.println(dp[h][w]);
    }

    private int mod(int a, int b) {
        return (a % MOD + b % MOD) % MOD;
    }
}
