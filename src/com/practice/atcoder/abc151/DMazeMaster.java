package com.practice.atcoder.abc151;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.PrintWriter;

public class DMazeMaster {

    static int[][] a;
    static int r, c;
    static int max;
    static int[][] dir = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        r = in.nextInt();
        c = in.nextInt();

        // Skip
        in.nextLine();

        a = new int[r][c];
        max = 0;

        for (int i = 0; i < r; i++) {
            String s = in.nextLine();
            for (int j = 0; j < c; j++) {
                if (s.charAt(j) == '.') {
                    a[i][j] = 1;
                } else {
                    a[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        out.println(max);
    }

    private void bfs(int i, int j) {
        int[][] dist = new int[r][c];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        while (!q.isEmpty()) {
            Pair p = q.remove();
            for (int k = 0; k < dir.length; k++) {
                int ki = p.x + dir[k][0];
                int kj = p.y + dir[k][1];
                if (notPossible(ki, kj) || dist[ki][kj] > 0 || (ki == i && kj == j)) continue;
                q.add(new Pair(ki, kj));
                dist[ki][kj] = dist[p.x][p.y] + 1;
                if (dist[ki][kj] > max) {
                    max = dist[ki][kj];
                }
            }
        }
    }

    private boolean notPossible(int i, int j) {
        return i >= r || j >= c || i < 0 || j < 0 || a[i][j] == 0;
    }

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
