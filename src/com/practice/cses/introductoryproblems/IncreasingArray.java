package com.practice.cses.introductoryproblems;

import java.io.PrintWriter;
import java.util.Scanner;

public class IncreasingArray {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        int prev = 0;
        long gc = 0;
        for (int i = 0; i < n; i++) {
            int curr = in.nextInt();
            if (curr < prev) {
                gc += prev - curr;
            } else {
                prev = curr;
            }
        }

        out.println(gc);
    }
}
