package com.practice.codechef.djdolls.longchallenge.april12;

import java.util.Scanner;
import java.io.PrintWriter;

public class DoubleStrings {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int M = 0xfffffffe;
            int N = in.nextInt();
            out.println(N & M);
        }
    }
}
