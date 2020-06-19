package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class Permutations {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        if (n == 1) {
            out.println(1);
            return;
        }

        if (n <= 3) {
            out.println("NO SOLUTION");
            return;
        }

        for (int i = 2; i <= n; i = i+2) {
            out.print(i + " ");
        }

        for (int i = 1; i <= n; i = i+2) {
            out.print(i + " ");
        }

        out.println();
    }
}
