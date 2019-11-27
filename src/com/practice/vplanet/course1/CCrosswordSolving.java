package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CCrosswordSolving {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        in.nextLine(); // Consume end of line
        String s = in.nextLine();
        String t = in.nextLine();

        List<Integer> l = null;
        int d = Integer.MAX_VALUE;
        for (int i = 0; i <= m-n; i++) {
            List<Integer> c = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) != t.charAt(i+j)) {
                    c.add(j+1);
                }
            }
            if (c.size() < d) {
               d = c.size();
               l = c;
            }
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < l.size(); i++) {
            sb.append(l.get(i) + " ");
        }

        out.println(l.size());
        out.println(sb.toString());
    }
}
