package com.practice.atcoder.abc154;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Set;

public class CDistinctOrNot {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(in.nextInt());
        }
        if (set.size() == n) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
