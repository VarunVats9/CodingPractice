package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class BitStrings {

    private int MOD = 1000000007;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        out.println(power(n));
    }

    private long power(int n) {
        if (n == 0) return 1;
        long a = power(n/2);
        if ((n & 1) > 0) {
            return mod(a, a, 2);
        } else {
            return mod(a, a);
        }
    }

    private long mod(long x, long y) {
        return ((x % MOD) * (y % MOD)) % MOD;
    }

    private long mod(long x, long y, long z) {
        return ((z % MOD) * (mod(x, y) % MOD)) % MOD;
    }
}
