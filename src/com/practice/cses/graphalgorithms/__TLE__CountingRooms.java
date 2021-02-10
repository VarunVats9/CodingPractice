package com.practice.cses.graphalgorithms;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class __TLE__CountingRooms {

    static char[][] matrix;
    static boolean[][] visited;
    static int n, m;
    static int[][] dir = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        matrix = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            matrix[i] = in.nextLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '.' && !visited[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        out.println(count);
    }

    private void dfs(int i, int j) {
        visited[i][j] = true;
        for (int k = 0; k < dir.length; k++) {
            int ni = i + dir[k][0];
            int nj = j + dir[k][1];
            if (isValid(ni, nj)) {
                dfs(ni, nj);
            }
        }
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < m && matrix[i][j] != '#' && !visited[i][j];
    }
}
