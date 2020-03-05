package com.practice.chelper;

import java.util.Scanner;
import java.io.PrintWriter;

public class FLCS {

    private static int max = 3000;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextLine();
        String t = in.nextLine();

        int[][] dp = new int[max+1][max+1];

        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= max; j++) {

            }
        }

        out.println(dp[max][max]);
    }
}
