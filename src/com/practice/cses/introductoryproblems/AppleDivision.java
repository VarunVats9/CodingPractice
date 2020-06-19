package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class AppleDivision {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long sum = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }

        long diff = Long.MAX_VALUE;
        int max = (int) Math.pow(2, n);
        for (int i = 1; i <= max; i++) {
            int j = i, c = 0;
            long s = 0;
            while (c < n) {
                if ((j & 1) > 0) {
                    s += arr[c];
                }
                c++;
                j >>= 1;
            }
            diff = Math.min(diff, Math.abs(2*s - sum));
        }

        out.println(diff);
    }
}
