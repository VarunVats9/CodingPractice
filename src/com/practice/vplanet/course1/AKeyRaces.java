package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class AKeyRaces {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int s = in.nextInt();
        int v1 = in.nextInt();
        int v2 = in.nextInt();
        int t1 = in.nextInt();
        int t2 = in.nextInt();

        int p1 = s*v1 + 2*t1;
        int p2 = s*v2 + 2*t2;

        if (p1 < p2) {
            out.println("First");
        } else if (p2 < p1) {
            out.println("Second");
        } else {
            out.println("Friendship");
        }
    }
}
