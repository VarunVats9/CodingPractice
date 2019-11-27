package com.practice.vplanet.course1;

import java.io.PrintWriter;
import java.util.Scanner;

public class GBargainingTable {

    static int[][] a = new int[25][25];
    static int r, c;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        r = in.nextInt();
        c = in.nextInt();

        in.nextLine(); // Consume end of line

        for (int i = 0; i < r; i++) {
            String s = in.nextLine();
            for (int j = 0; j < c; j++) {
                a[i][j] = (s.charAt(j) - '0');
            }
        }

        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] == 0) {
                    max = Math.max(max, Math.max(columnWise(i, j), rowWise(i, j)));
                }
            }
        }

        out.println(max);
    }

    private int rowWise(int i, int j) {
        int R = 0;
        int C = 1;

        // Get maximum rows
        for (int k = i; k < r; k++) {
            if (a[k][j] == 0) {
                R++;
                continue;
            }
            break;
        }

        // Get maximum columns
        for (int k = j+1; k < c; k++) {
            boolean exit = false;
            for (int l = i; l < i+R; l++) {
                if (a[l][k] != 0) {
                    exit = true;
                    break;
                }
            }
            if (exit) {
                break;
            }
            C++;
        }
        return 2 * (R + C);
    }

    private int columnWise(int i, int j) {
        int R = 1;
        int C = 0;

        // Get maximum columns
        for (int k = j; k < c; k++) {
            if (a[i][k] == 0) {
                C++;
                continue;
            }
            break;
        }

        // Get maximum rows
        for (int k = i+1; k < r; k++) {
            boolean exit = false;
            for (int l = j; l < j+C; l++) {
                if (a[k][l] != 0) {
                    exit = true;
                    break;
                }
            }
            if (exit) {
                break;
            }
            R++;
        }
        return 2 * (R + C);
    }
}
