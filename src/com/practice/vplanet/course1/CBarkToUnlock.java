package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class CBarkToUnlock {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String pwd = in.nextLine();
        char end = pwd.charAt(0);
        char start = pwd.charAt(1);
        boolean foundStart = false;
        boolean foundEnd = false;
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String bark = in.nextLine();
            if (bark.equals(pwd)) {
                out.println("YES");
                return;
            }
            if (bark.charAt(0) == start) {
                foundStart = true;
            }
            if (bark.charAt(1) == end) {
                foundEnd = true;
            }
        }

        if (foundStart && foundEnd) {
            out.println("YES");
            return;
        }

        out.println("NO");
    }
}
