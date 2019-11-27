package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class BFafaAndTheGates {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine();

        int count = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'U') {
                count++;
            }
            if (s.charAt(i) == 'R') {
                count--;
            }
            if (count == 0 && i+1 < n && s.charAt(i+1) == s.charAt(i)) {
                ans++;
            }
        }

        out.println(ans);
    }
}
