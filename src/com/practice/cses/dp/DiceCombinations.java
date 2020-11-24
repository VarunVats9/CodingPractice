package com.practice.cses.dp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DiceCombinations {

    static Map<Integer, Integer> memo = new HashMap<>();
    static long[] dp = new long[1_000_005];
    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] dp = new long[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i-j < 0) continue;
                dp[i] = (dp[i] + dp[i-j]) % MOD;
            }
        }
        out.println(dp[n]);
    }

//    public void solve(int testNumber, InputReader in, PrintWriter out) {
//        int n = in.nextInt();
//        memo.clear();
//        memo.put(0, 1);
//        out.println(func(n));
//    }
//
//    private int func(int n) {
//        if (n < 0) return 0;
//        if (memo.containsKey(n)) return memo.get(n);
//        int sum = 0;
//        for (int i = 1; i <= 6; i++) {
//            sum = (sum % MOD + func(n - i) % MOD) % MOD;
//        }
//        memo.put(n, sum);
//        return sum;
//    }
}
