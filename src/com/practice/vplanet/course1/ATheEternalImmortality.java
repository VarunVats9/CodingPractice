package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class ATheEternalImmortality {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();

        if (b-a >= 10) {
            out.println(0);
            return;
        }

        long rem = 1;
        for (long i = a+1; i <= b; i++) {
            rem = (rem * (i % 10)) % 10;
        }

        out.println(rem);
    }
}
