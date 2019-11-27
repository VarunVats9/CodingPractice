package com.practice.codechef.djdolls.longchallenge.april12;

import java.util.Scanner;
import java.io.PrintWriter;

public class FitToPlay {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int MIN = in.nextInt();
            int N = in.nextInt();
            int ans = 0;
            for (int i = 1; i < N; i++) {
                int X = in.nextInt();
                if (X < MIN) {
                    MIN = X;
                } else {
                    ans = Math.max(ans, X - MIN);
                }
            }
            if (ans == 0) {
                out.println("UNFIT");
            } else {
                out.println(ans);
            }
        }
    }
}
