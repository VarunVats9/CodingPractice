package com.practice.skiena.ch1;

import java.util.Scanner;
import java.io.PrintWriter;

public class __TheTrip {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;

            float[] arr = new float[n];
            float sum = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextFloat();
                sum += arr[i];
            }

            int avg = (int) ((sum / n) * 100);
            float truncAvg = ((float) avg) / 100;

            float ans = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] < truncAvg) {
                    ans += truncAvg - arr[i];
                }
            }

            out.println("$" + String.format("%.2f", ans));
        }
    }
}
