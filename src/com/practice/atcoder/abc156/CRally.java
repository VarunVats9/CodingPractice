package com.practice.atcoder.abc156;

import java.io.PrintWriter;
import java.util.Scanner;

public class CRally {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int stamina = Integer.MAX_VALUE;

        for (int i = 1; i <= 100; i++) {
            int st = 0;
            for (int j = 0; j < n; j++) {
                st += Math.pow(i - arr[j], 2);
            }
            stamina = Math.min(st, stamina);
        }

        out.println(stamina);
     }
}
