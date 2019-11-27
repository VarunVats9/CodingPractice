package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class DFafaAndHisCompany {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int count = 0;
        for (int i = 1; i <= n/2; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        out.println(count);
    }
}
