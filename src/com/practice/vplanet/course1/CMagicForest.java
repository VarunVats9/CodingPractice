package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class CMagicForest {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        int count = 0;
        for (int a = 1; a <= n; a++) {
            for (int b = a; b <= n; b++) {
                int c = a ^ b;
                if (a + b > c && c <= n && c >= b) {
                    count++;
                }
            }
        }

        out.println(count);
    }
}
