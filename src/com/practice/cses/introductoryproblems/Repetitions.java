package com.practice.cses.introductoryproblems;

import java.io.PrintWriter;
import java.util.Scanner;

public class Repetitions {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();

        int gc = 0;
        int lc = 0;
        char prev = 'X';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != prev) {
                lc = 1;
                prev = s.charAt(i);
            } else {
                lc++;
            }
            gc = Math.max(gc, lc);
        }

        out.println(gc);
    }
}
