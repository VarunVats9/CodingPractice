package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class CChessForThree {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        for (int i = 0; i < n; i++) {
            int win = in.nextInt();
            if (set.contains(win)) {
                set.remove(win);
                int lose = set.iterator().next();
                set.remove(lose);
                int replace = getThird(win, lose);
                set.add(replace);
                set.add(win);
            } else {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }

    private int getThird(int win, int lose) {
        if (win == 1) {
            if (lose == 2) return 3;
            return 2;
        } else if (win == 2) {
            if (lose == 1) return 3;
            return 1;
        } else {
            if (lose == 1) return 2;
            return 1;
        }
    }
}
