package com.practice.skiena.ch1;

import java.io.PrintWriter;
import java.util.Scanner;

public class Minesweeper {

    static int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int test = 0;
        while (in.hasNext()) {
            test++;
            int r = in.nextInt();
            int c = in.nextInt();
            if (r == 0 && c == 0) break;

            // Skip a line
            in.nextLine();
            char[][] mine = new char[r][c];
            for (int i = 0; i < r; i++) {
                String s = in.nextLine();
                for (int j = 0; j < s.length(); j++) {
                    mine[i][j] = s.charAt(j);
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (mine[i][j] != '*') {
                        int count = 0;
                        for (int k = 0; k < DIRECTIONS.length; k++)
                            if (valid(i + DIRECTIONS[k][0], j + DIRECTIONS[k][1], r, c)
                                    && mine[i + DIRECTIONS[k][0]][j + DIRECTIONS[k][1]] == '*') count++;
                        mine[i][j] = (char) (count + '0');
                    }
                }
            }

            if (test != 1) {
                out.println();
            }
            out.println("Field #" + test + ":");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    out.print(mine[i][j]);
                }
                out.println();
            }
        }
    }

    private boolean valid(int i, int j, int r, int c) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }
}
