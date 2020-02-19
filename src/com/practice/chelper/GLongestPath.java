package com.practice.chelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class GLongestPath {

    public static long[] dp;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        ArrayList<Integer>[] vert = new ArrayList[n+1];
        for (int i = 0; i < vert.length; i++) {
            vert[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            vert[x].add(y);
        }

        dp = new long[n+1];
        Arrays.fill(dp, -1);


    }
}
