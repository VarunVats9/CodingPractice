package com.practice.cses.introductoryproblems;

import java.util.*;
import java.io.PrintWriter;

public class ChessboardAndQueens {

    int SIZE = 8;
    char[][] chess = new char[SIZE][SIZE];
    boolean[] col = null;
    int count = 0;
    Map<String, List<String>> diag = new HashMap<>();
    Map<String, List<String>> anti = new HashMap<>();

    public void solve(int testNumber, Scanner in, PrintWriter out) {

        for (int i = 0; i < SIZE; i++) {
            String s = in.nextLine();
            chess[i] = s.toCharArray();
        }

        col = new boolean[SIZE];
        count = 0;
        diag.clear(); anti.clear();
        recurse(0);
        out.println(count);
    }

    private void recurse(int idx) {
        if (idx >= SIZE) {
            //printChess();
            count++;
            return;
        }

        for (int j = 0; j < SIZE; j++) {
            if (chess[idx][j] != '*' && !col[j] && chess[idx][j] != 'Q' && noDiagonalFall(idx, j)) {
                preProcessDiag(idx, j);
                recurse(idx+1);
                postProcessDiag(idx, j);
            }
        }
    }

    private void printChess() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println(chess[i]);
        }

    }

    private void preProcessDiag(int i, int j) {
        col[j] = true;
        chess[i][j] = 'Q';

        List<String> l = new ArrayList<>();
        int r = i, c = j;
        while (r < SIZE && c < SIZE) {
            l.add(r + "" + c);
            r++; c++;
        }
        diag.put((i + "" + j), l);

        List<String> a = new ArrayList<>();
        int r1 = i, c1 = j;
        while (r1 < SIZE && c > 0) {
            a.add(r1 + "" + c1);
            r1++; c1--;
        }
        anti.put((i + "" + j), a);
    }

    private void postProcessDiag(int i, int j) {
        col[j] = false;
        chess[i][j] = '.';

        diag.remove(i + "" + j);
        anti.remove(i + "" + j);
    }

    private boolean noDiagonalFall(int i, int j) {
        for (Map.Entry<String, List<String>> entry : diag.entrySet()) {
            if (entry.getValue().contains(i + "" + j)) {
                return false;
            }
        }
        for (Map.Entry<String, List<String>> entry : anti.entrySet()) {
            if (entry.getValue().contains(i + "" + j)) {
                return false;
            }
        }

        return true;
    }
}
