package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class HTetris {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int s = in.nextInt();

        int points = Integer.MAX_VALUE;
        int[] a = new int[n+1];
        for (int i = 0; i < s; i++) {
            int p = in.nextInt();
            a[p]++;
        }
        for (int i = 1; i <= n; i++) {
            points = Math.min(points, a[i]);
        }
        out.println(points);
    }
}
