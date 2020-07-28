package com.practice.chelper;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class ARequiredRemainder {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            int m = in.nextInt();
            out.println(s * ((m - t) / s) + t);
        }
    }
}
