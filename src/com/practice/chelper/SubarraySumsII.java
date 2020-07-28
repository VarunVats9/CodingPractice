package com.practice.chelper;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class SubarraySumsII {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), t = in.nextInt();
        int[] arr = in.readIntArray(n);

        int p = 0, c = 0;
        int sum = 0, count = 0;

        while (c < n) {
            while (sum <= t && c < n) {
                sum += arr[c];
                c++;
                if (sum == t) {
                    count++;
                }
            }
            while (sum >= t && p < n) {
                sum -= arr[p];
                p++;
                if (sum == t) {
                    count++;
                }
            }
        }

        out.println(count);
    }
}
