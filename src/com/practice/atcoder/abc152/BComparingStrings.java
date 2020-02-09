package com.practice.atcoder.abc152;

import java.util.Scanner;
import java.io.PrintWriter;

public class BComparingStrings {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();

        StringBuilder ar = new StringBuilder();
        StringBuilder br = new StringBuilder();

        for (int i = 0; i < b; i++) {
            ar.append(a);
        }

        for (int j = 0; j < a; j++) {
            br.append(b);
        }

        int ans = ar.toString().compareTo(br.toString());

        if (ans > 0) out.println(br);
        else out.println(ar);
    }
}
