package com.practice.cses.graphalgorithms;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class __TLE__Monsters {
    static char[][] matrix;
    static int[][] timerGrid;
    static Pair<Integer, Integer> entry;
    static Pair<Integer, Integer> exit;
    static Set<Pair<Integer, Integer>> visited;
    static int n, m;
    static int[][] dir = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static Character[] move = new Character[] {'U', 'L', 'D', 'R'};
    static Pair<Pair<Integer, Integer>, Character>[][] parentMatrix;
    static Queue<Pair<Pair<Integer, Integer>, Integer>> queue;
    static boolean found;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        matrix = new char[n][m];
        visited = new HashSet<>();
        queue = new LinkedList<>();
        parentMatrix = new Pair[n][m];
        timerGrid = new int[n][m];

        for (int i = 0; i < n; i++) {
            matrix[i] = in.nextLine().toCharArray();
            Arrays.fill(timerGrid[i], -1);
        }

        // pre-process all monsters' shortest time to reach the cell.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (matrix[i][j]) {
                    case 'M':
                        timerGrid[i][j] = 0;
                        queue.add(Pair.makePair(Pair.makePair(i, j), 0));
                        visited.add(Pair.makePair(i, j));
                        break;
                    case 'A':
                        entry = Pair.makePair(i, j);
                        break;
                    default:
                        break;
                }
            }
        }
        mssp();

        // Based on time, check if a cell is reached by monsters before.
        queue.clear();
        visited.clear();
        bfs();

        if (!found) {
            out.println("NO");
        } else {
            out.println("YES");
            String path = makeThePath(new StringBuilder(""));
            out.println(path.length());
            out.println(path);
        }
    }

    private void mssp() {
        while (!queue.isEmpty()) {
            Pair<Pair<Integer, Integer>, Integer> top = queue.poll();
            Pair<Integer, Integer> pair = top.first;
            int pLevel = top.second;
            for (int k = 0; k < dir.length; k++) {
                int ni = pair.first + dir[k][0];
                int nj = pair.second + dir[k][1];
                if (isValid(ni, nj)) {
                    timerGrid[ni][nj] = pLevel+1;
                    queue.add(Pair.makePair(Pair.makePair(ni, nj), pLevel+1));
                    visited.add(Pair.makePair(ni, nj));
                }
            }
        }
    }

    private String makeThePath(StringBuilder stringBuilder) {
        if (exit.equals(entry)) {
            return stringBuilder.reverse().toString();
        }
        stringBuilder.append(parentMatrix[exit.first][exit.second].second);
        exit = parentMatrix[exit.first][exit.second].first;
        return makeThePath(stringBuilder);
    }

    private void bfs() {
        if (isValidExitDoor(entry.first, entry.second)) {
            found = true;
            exit = entry;
            return;
        }
        queue.add(Pair.makePair(entry, 0));
        while (!queue.isEmpty()) {
            Pair<Pair<Integer, Integer>, Integer> top = queue.poll();
            Pair<Integer, Integer> pair = top.first;
            int time = top.second;
            for (int k = 0; k < dir.length; k++) {
                int ni = pair.first + dir[k][0];
                int nj = pair.second + dir[k][1];
                int nTime = time+1;
                if (isValidCell(ni, nj, nTime)) {
                    parentMatrix[ni][nj] = Pair.makePair(pair, move[k]);
                    if (isValidExitDoor(ni, nj)) {
                        found = true;
                        exit = Pair.makePair(ni, nj);
                        return;
                    }
                    queue.add(Pair.makePair(Pair.makePair(ni, nj), nTime));
                    visited.add(Pair.makePair(ni, nj));
                }
            }
        }
    }

    private boolean isValidExitDoor(int i, int j) {
        return (i == 0 || j == 0 || i == n-1 || j == m-1) && matrix[i][j] != '#';
    }

    private boolean isValidCell(int i, int j, int time) {
        return i >= 0 && j >= 0 && i < n && j < m && matrix[i][j] != '#' && !visited.contains(Pair.makePair(i, j)) && (time < timerGrid[i][j] || timerGrid[i][j] == -1);
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < m && matrix[i][j] != '#' && !visited.contains(Pair.makePair(i, j));
    }
}
