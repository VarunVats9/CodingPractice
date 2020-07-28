package com.practice.cses.introductoryproblems;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class __TLE__GridPaths {

    int SIZE = 7;
    int PATH_SIZE = 48;
    boolean[][] board = null;
    int[][] DIR = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    char[] POS = new char[] {'R', 'D', 'L', 'U'};
    String s = null;
    char[] p = null;
    int count = 0;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        s = in.nextLine();
        board = new boolean[SIZE][SIZE];
        p = new char[PATH_SIZE];
        count = 0;
        Arrays.fill(p, '_');
        recurse(0, 0, 0);
        out.println(count);
    }

    private void recurse(int r, int c, int idx) {
        if (idx == PATH_SIZE) {
            if (r == SIZE - 1 && c == 0) {
                count++;
            }
            return;
        }

        for (int i = 0; i < DIR.length; i++) {
            int x = r + DIR[i][0];
            int y = c + DIR[i][1];
            if (validPath(x, y, POS[i], idx)) {
                board[r][c] = true;
                p[idx] = POS[i];
                recurse(x, y, idx+1);
                board[r][c] = false;
                p[idx] = '_';
            }
        }
    }

    private boolean validPath(int x, int y, char po, int idx) {
        return  (s.charAt(idx) == '?' || po == s.charAt(idx))
                && x >= 0 && x < SIZE && y >= 0 && y < SIZE && !board[x][y];
    }
}
