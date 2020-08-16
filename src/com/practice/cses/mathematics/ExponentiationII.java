package com.practice.cses.mathematics;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class ExponentiationII {

    private static int MOD = 1_000_000_000 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
            out.println(exponential(a, exponential(b, c, MOD-1), MOD));
        }
    }

    private int exponential(int a, int b, int m) {
        if (b == 0) return 1;
        int res = exponential(a, b/2, m);
        int ans = mod(res, res, m);
        if (b % 2 == 1) ans = mod(ans, a, m);
        return ans;
    }

    private int mod(int a, int b, int m) {
        return (int) (((long)a % m * (long)b % m) % m);
    }
}
