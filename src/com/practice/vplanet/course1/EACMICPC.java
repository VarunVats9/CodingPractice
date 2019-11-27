package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class EACMICPC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        String[] t = s.split(" ");

        int[] a = new int[t.length];
        int sum = 0;
        for (int i = 0; i < t.length; i++) {
            a[i] = Integer.parseInt(t[i]);
            sum += a[i];
        }

        if (sum % 2 != 0) {
            out.println("NO");
            return;
        }

        int p = a.length;
        for (int i = 0; i < p-2; i++) {
            for (int j = i+1; j < p-1; j++) {
                for (int k = j+1; k < p; k++) {
                    if ((a[i] + a[j] + a[k]) == sum/2) {
                        out.println("YES");
                        return;
                    }
                }
            }
        }
        out.println("NO");
    }
}
