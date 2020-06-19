package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class TwoSets {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextInt();
        long sum = (n * (n+1)) / 2;
        if ((sum & 1) > 0) {
            out.println("NO");
            return;
        }

        sum /= 2;
        int m = (int) n;

        boolean[] arr = new boolean[m+1];
        int idx = m;
        int c = 0;
        while (sum != 0) {
            if (sum - idx < 0) {
                arr[(int)sum] = true;
                c++;
                break;
            }
            sum -= idx;
            arr[idx] = true;
            idx--;
            c++;
        }

        out.println("YES");
        out.println(c);
        for (int i = 1; i <= n; i++) {
            if (arr[i]) {
                out.print(i + " ");
            }
        }
        out.println();

        out.println(n - c);
        for (int i = 1; i <= n; i++) {
            if (!arr[i]) {
                out.print(i + " ");
            }
        }
        out.println();
    }
}
