package com.practice.atcoder.algoskill;

import java.util.Scanner;
import java.io.PrintWriter;

public class DDuplicated {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int[] arr = new int[2000005];
        int n = in.nextInt();

        for (int i = 1; i <= n; i++) {
            arr[in.nextInt()] += 1;
        }

        int a = 0, b = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] == 2) {
                a = i;
            }

            if (arr[i] == 0) {
                b = i;
            }
        }

        if (a == 0 || b == 0) {
            out.println("Correct");
        } else {
            out.println(a + " " + b);
        }
    }
}
