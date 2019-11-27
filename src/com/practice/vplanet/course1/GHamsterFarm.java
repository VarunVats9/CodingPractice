package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class GHamsterFarm {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong();
        long k = in.nextLong();

        long min = Long.MAX_VALUE;
        long ans1 = 0;
        long ans2 = 0;
        for (int i = 1; i <= k; i++) {
            long b = in.nextLong();
            long left = n % b;
            if (left < min) {
                min = left;
                ans1 = i;
                ans2 = n / b;
            }
        }
        out.println(ans1 + " " + ans2);
    }
}
