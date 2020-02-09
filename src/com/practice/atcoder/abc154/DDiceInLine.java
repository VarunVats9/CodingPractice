package com.practice.atcoder.abc154;

import java.util.Scanner;
import java.io.PrintWriter;

public class DDiceInLine {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        double[] arr = new double[n+1];
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            arr[i] = (in.nextInt() + 1) / 2.0;
            if (i <= k) sum += arr[i];
        }

        double max = Math.max(sum, 0);
        for (int j = 1, i = k+1; i <= n; i++, j++) {
            sum += arr[i] - arr[j];
            max = Math.max(sum, max);
        }

        out.printf("%.12f", max);
    }
}
