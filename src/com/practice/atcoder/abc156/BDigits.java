package com.practice.atcoder.abc156;

import java.io.PrintWriter;
import java.util.Scanner;

public class BDigits {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int count = 0;

        while (n > 0) {
            n = n / k;
            count++;
        }

        out.println(count);
    }
}
