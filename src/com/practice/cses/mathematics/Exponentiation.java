package com.practice.cses.mathematics;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class Exponentiation {

    private static int MOD = 1_000_000_000 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            out.println(exponential(in.nextInt(), in.nextInt()));
        }
    }

    private int exponential(int a, int b) {
        if (b == 0) return 1;
        int res = exponential(a, b/2);
        int ans = mod(res, res);
        if (b % 2 == 1) ans = mod(ans, a);
        return ans;
    }

    private int mod(int a, int b) {
        return (int) (((long)a % MOD * (long)b % MOD) % MOD);
    }
}
