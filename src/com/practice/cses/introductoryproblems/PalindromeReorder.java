package com.practice.cses.introductoryproblems;

import java.util.Scanner;
import java.io.PrintWriter;

public class PalindromeReorder {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        int[] c = new int[26];

        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'A']++;
        }

        int oc = 0;
        char o = '-';
        for (int i = 0; i < c.length; i++) {
            if (c[i] % 2 > 0) {
                o = (char) ('A' + i);
                c[i]--;
                oc++;
            }
            c[i] /= 2;
        }

        if (oc > 1) {
            out.println("NO SOLUTION");
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < c.length; i++) {
            for (int j = 1; j <= c[i]; j++) {
                sb.append((char) ('A' + i));
            }
        }

        String h = sb.toString();
        String t = sb.reverse().toString();
        out.println(h + (('-' != o) ? o : "") + t);
    }
}
