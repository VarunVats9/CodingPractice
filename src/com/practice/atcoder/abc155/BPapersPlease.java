package com.practice.atcoder.abc155;

import java.util.Scanner;
import java.io.PrintWriter;

public class BPapersPlease {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            int num = in.nextInt();
            if (num % 2 == 0) {
                if (!(num % 3 == 0 || num % 5 == 0)) {
                    out.println("DENIED");
                    return;
                }
            }
        }
        out.println("APPROVED");
    }
}
