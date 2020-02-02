package com.practice.atcoder.abc151;

import java.util.Scanner;
import java.io.PrintWriter;

public class FEncloseAll {

    static int n;
    static int[] x, y;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        x = new int[n];
        y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        double l = 0, r = 1000;
        for (int i = 1; i <= 80; i++) {
            double m1 = l + (r-l) / 3;
            double m2 = r - (r-l) / 3;
            if (g(m1) > g(m2))
                l = m1;
            else r = m2;
        }

        double ans = g(l);
        out.printf("%.10f\n", ans);
    }

    double g(double x) {
        double l = 0, r = 1000;
        for (int i = 1; i <= 80; i++) {
            double m1 = l + (r-l) / 3;
            double m2 = r - (r-l) / 3;
            if (f(x, m1) > f(x, m2))
                l = m1;
            else r = m2;
        }

        return f(x, l);
    }

    double f(double x1, double y1) {
        double res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dist(x[i], y[i], x1, y1));
        }
        return res;
    }

    double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;

        return Math.sqrt(dx*dx + dy*dy);
    }
}
