package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class TwoKnights {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            long tn = i*i, pn = (tn*(tn-1))/2;
            if (i > 2) {
                pn -= 4*(i-1)*(i-2);
            }
            out.println(pn);
        }
    }
}
