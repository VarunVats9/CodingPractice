package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class TrailingZeros {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int ans = 0;
        for (int i = 5; i <= n; i = i*5) {
            ans += n/i;
        }
        out.println(ans);
    }
}
