package com.practice.atcoder.abc152;

import java.util.Scanner;
import java.io.PrintWriter;

public class AACOrWA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        if (n == m) {
            out.println("Yes");
            return;
        }

        out.println("No");
    }
}
