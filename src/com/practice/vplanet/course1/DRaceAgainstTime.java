package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class DRaceAgainstTime {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int h = in.nextInt() % 12;
        int m = in.nextInt() / 5;
        int s = in.nextInt() / 5;
        int t1 = in.nextInt() % 12;
        int t2 = in.nextInt() % 12 ;

        out.println(possible(h, m, s, t1, t2));
    }

    private String possible(int h, int m, int s, int t1, int t2) {
        if (t1 > t2)
            return possible(h, m, s, t2, t1);

        int hands = 0;

        if (h >= t1 && h < t2) {
            hands++;
        }

        if (m >= t1 && m < t2) {
            hands++;
        }

        if (s >= t1 && s < t2) {
            hands++;
        }

        if (hands == 3 || hands == 0) return "YES";

        return "NO";
    }
}
