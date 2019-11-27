package com.practice.codechef.djdolls.longchallenge.april12;

import java.util.Scanner;
import java.io.PrintWriter;

public class GreatestDumplingFight {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            long a = in.nextLong();
            long b = in.nextLong();
            long c = in.nextLong();
            long d = in.nextLong();
            long k = in.nextLong();

            long d1 = gcd(a, b);
            long d2 = gcd(c, d);

            long g = gcd(d1, d2);
            d2 = (d2/g);

            if (d1 > k/d2) {
                out.println(1);
            } else {
                long ans = (k / (d1 * d2));
                ans <<= 1;
                ans |= 1;
                out.println(ans);
            }
        }
    }

    private long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
