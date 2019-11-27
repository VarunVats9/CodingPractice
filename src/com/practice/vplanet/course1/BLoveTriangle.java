package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class BLoveTriangle {

    static int[] a = new int[5001];

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            if (loveTriangle(i, 0, a[i])) {
                out.println("YES");
                return;
            }
        }
        out.println("NO");
    }

    private boolean loveTriangle(int i, int count, int start) {
        if (a[i] == i) return false;

        if (count == 3) {
            return start == a[i];
        }

        return loveTriangle(a[i], count+1, start);
    }
}
