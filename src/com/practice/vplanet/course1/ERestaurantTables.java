package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class ERestaurantTables {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        int c = 0;
        int denied = 0;
        for (int i = 0; i < n; i++) {
            int g = in.nextInt();
            if (g == 2) {
                if (b > 0) b--;
                else denied = denied+2;
            } else {
                if (a > 0) a--;
                else if (b > 0) {b--; c++;}
                else if (c > 0) c--;
                else denied++;
            }
        }
        out.println(denied);
    }
}
