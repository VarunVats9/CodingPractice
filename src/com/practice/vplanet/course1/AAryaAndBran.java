package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class AAryaAndBran {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int left = 0, total = 0;
        for (int i = 1; i <= n; i++) {
            int c = in.nextInt();
            total = left + c;
            int max = Math.min(8, total);
            k = k - max;
            if (k <= 0) {
                out.println(i);
                return;
            }
            left = total - max;
        }
        out.println(-1);
    }
}
