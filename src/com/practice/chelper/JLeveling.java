package com.practice.chelper;

import java.util.Scanner;
import java.io.PrintWriter;

public class JLeveling {

    static int[][] arr;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int h = in.nextInt();
        int w = in.nextInt();

        arr = new int[h+1][w+1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                arr[i][j] = in.nextInt();
            }
        }
    }
}
