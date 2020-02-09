package com.practice.atcoder.abc152;

import java.util.Scanner;
import java.io.PrintWriter;

public class CLowElements {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int min = arr[0];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] > min) {
                count++;
            }
            min = Math.min(min, arr[i]);
        }

        out.println(n - count);
    }
}
