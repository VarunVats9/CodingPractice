package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class DBlackSquare {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int r = in.nextInt();
        int c = in.nextInt();

        in.nextLine(); // Consume end of line
        char a[][] = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = in.nextLine();
            a[i] = s.toCharArray();
        }

        int lr = Integer.MAX_VALUE;
        int lc = Integer.MAX_VALUE;
        int hr = Integer.MIN_VALUE;
        int hc = Integer.MIN_VALUE;

        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] == 'B') {
                    lr = Math.min(lr, i);
                    hr = Math.max(hr, i);
                    lc = Math.min(lc, j);
                    hc = Math.max(hc, j);
                    count++;
                }
            }
        }

        int dr = hr-lr+1;
        int dc = hc-lc+1;

        if (lr == Integer.MAX_VALUE) {
            out.println(1);
            return;
        }

        if (dr > r || dr > c || dc > r || dc > c) {
            out.println(-1);
            return;
        }

        out.println(Math.max(dr, dc) * Math.max(dr, dc) - count);
    }
}
