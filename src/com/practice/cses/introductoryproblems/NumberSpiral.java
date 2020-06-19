package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class NumberSpiral {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            long r = in.nextInt();
            long c = in.nextInt();
            if ((c & 1) > 0) {
                if (r <= c) {
                    out.println(c * c - r + 1);
                } else {
                    if ((r & 1) > 0) {
                        out.println((r-1) * (r-1) + c);
                    } else {
                        out.println(r * r - c + 1);
                    }
                }
            } else {
                if (r <= c) {
                    out.println((c-1) * (c-1) + r);
                } else {
                    if ((r & 1) > 0) {
                        out.println((r-1) * (r-1) + c);
                    } else {
                        out.println(r * r - c + 1);
                    }
                }
            }
        }

    }
}
