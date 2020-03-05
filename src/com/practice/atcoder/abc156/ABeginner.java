package com.practice.atcoder.abc156;

import java.io.PrintWriter;
import java.util.Scanner;

public class ABeginner {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int r = in.nextInt();

        if (n >= 10) {
            out.println(r);
        } else {
            out.println(r + 100*(10-n));
        }
    }
}
