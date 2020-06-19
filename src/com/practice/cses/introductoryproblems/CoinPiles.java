package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class CoinPiles {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (((a+b) % 3) > 0) {
                out.println("NO");
                continue;
            }

            int y = (2*a - b) / 3;
            int x = (2*b - a) / 3;

            if (x >= 0 && y >= 0) {
                out.println("YES");
            } else {
                out.println("NO");
            }

        }
    }
}
