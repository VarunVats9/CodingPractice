package com.practice.cses.mathematics;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class BinomialCoefficients {

    static int size = 1_000_003;
    static final int MOD = 1_000_000_007;
    static long[] f1 = new long[size];
    static long[] f2 = new long[size];
    static long[] inv = new long[size];

    static {
        inv[1] = 1;
        for (int i = 2; i < size; i++) {
            inv[i] = (MOD - MOD/i * inv[MOD%i] % MOD) % MOD;
        }


        f1[0] = 1; f2[0] = 1;
        for (int i = 1; i < size; i++) {
            f1[i] = mod(i, f1[i-1]);
            f2[i] = mod(inv[i], f2[i-1]);
        }
    }

    public static long mod(long a, long b) {
        return (a % MOD * b % MOD) % MOD;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            out.println(binomial(in.nextInt(), in.nextInt()));
        }
    }

    private long binomial(int a, int b) {
        return mod(f1[a], mod(f2[b], f2[a-b]));
    }
}
