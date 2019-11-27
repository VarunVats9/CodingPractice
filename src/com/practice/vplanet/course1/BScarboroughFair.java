package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class BScarboroughFair {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine(); // Consume left over new line
        String s = in.nextLine();

        char[] a = s.toCharArray();
        for (int i = 0; i < m; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            String c1c2 = in.nextLine();
            char c1 = c1c2.charAt(1);
            char c2 = c1c2.charAt(3);


            for (int j = l-1; j < r; j++) {
                if (a[j] == c1) a[j] = c2;
            }
        }

        out.println(String.valueOf(a));
    }
}
