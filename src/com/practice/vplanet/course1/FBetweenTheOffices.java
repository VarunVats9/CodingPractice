package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class FBetweenTheOffices {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine();

        char prev = s.charAt(0);
        int a = 0;
        int b = 0;
        for (int i = 1; i < n; i++) {
            if (prev != s.charAt(i)) {
                if (prev == 'S') a++;
                else b++;
            }
            prev = s.charAt(i);
        }

        if (a > b) {
            out.println("YES");
            return;
        }

        out.println("NO");
    }
}
