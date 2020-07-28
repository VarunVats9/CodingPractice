package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class MaximumSubarraySum {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.readIntArray(n);
        long sum = 0;
        long gc = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            gc = Math.max(gc, sum);
            if (sum < 0) {
                sum = 0;
            }
        }

        out.println(gc);
    }
}
