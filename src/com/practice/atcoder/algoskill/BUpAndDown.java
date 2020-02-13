package com.practice.atcoder.algoskill;

import java.util.Scanner;
import java.io.PrintWriter;

public class BUpAndDown {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int prev = in.nextInt();
        for (int i = 1; i < 10; i++) {
            int x = in.nextInt();
            if (x > prev) {
                out.println("up " + (x - prev));
            } else if (prev > x) {
                out.println("down " + (prev - x));
            } else {
                out.println("stay");
            }
            prev = x;
        }
    }
}
