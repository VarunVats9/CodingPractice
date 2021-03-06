package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class OMatching {

    static int MOD = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        // dp[i][mask] => 0 .. i MAN have been assigned, where ith bit of mask
        // tells if WOMAN has been assigned or not
        int[] dp = new int[1<<n];
        dp[0] = 1;
        for (int mask = 0; mask < (1<<n) - 1; mask++) {
            int x = Integer.bitCount(mask);
            for (int y = 0; y < n; y++) {
                if (arr[x][y] == 1 && (mask & (1<<y)) == 0) {
                    int turnOffTheBit = mask ^ (1<<y);
                    dp[turnOffTheBit] = mod(dp[mask], dp[turnOffTheBit]);
                }
            }
        }

        out.println(dp[(1<<n)-1]);
    }

    private int mod(int a, int b) {
        return (a % MOD + b % MOD) % MOD;
    }
}
