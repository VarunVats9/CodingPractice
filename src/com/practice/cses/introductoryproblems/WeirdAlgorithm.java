package com.practice.cses.introductoryproblems;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeirdAlgorithm {

    static List<Long> l = new ArrayList<>();

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        l.clear();
        StringBuilder sb = new StringBuilder("");
        long j = in.nextInt();
        long k = j;
        l.add(k);
        while (k != 1) {
            assert k >= 0 : "K can never be negative";
            if (k % 2 > 0) {
                k = 3 * k + 1;
            } else {
                k = k / 2;
            }
            l.add(k);
        }
        for (Long x : l) sb.append(x).append(" ");
        out.println(sb.toString());
    }
}
