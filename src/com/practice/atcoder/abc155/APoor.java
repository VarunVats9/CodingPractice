package com.practice.atcoder.abc155;

import java.util.Scanner;
import java.io.PrintWriter;

public class APoor {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if ((a == b && b != c) || (b == c && c != a)  || (c == a && b != a)) {
            out.println("Yes");
            return;
        }

        out.println("No");
    }
}
