package com.practice.cses.graphalgorithms;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collector;

public class __TLE__Labyrinth {

    public static final String EMPTY = "";
    static char[][] matrix;
    static Set<Pair<Integer, Integer>> visited;
    static int n, m;
    static int[][] dir = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static Character[] move = new Character[] {'U', 'L', 'D', 'R'};
    static Pair<Pair<Integer, Integer>, Character>[][] parentMatrix;
    static Queue<Pair<Integer, Integer>> queue;
    static Pair<Integer, Integer> foundAt;
    static Pair<Integer, Integer> SENTINEL= Pair.makePair(-1, -1);

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        matrix = new char[n][m];
        visited = new HashSet<>();
        queue = new LinkedList<>();
        parentMatrix = new Pair[n][m];
        foundAt = SENTINEL;

        for (int i = 0; i < n; i++) {
            matrix[i] = in.nextLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'A') {
                    queue.add(Pair.makePair(i, j));
                    parentMatrix[i][j] = Pair.makePair(SENTINEL, 'X');
                    visited.add(Pair.makePair(i, j));
                    String ans = bfs();
                    out.println(ans);
                    if (ans.equals("YES")) {
                        String path = makeThePath(new StringBuilder(""));
                        out.println(path.length());
                        out.println(path);
                    }
                }
            }
        }

    }

    private String makeThePath(StringBuilder stringBuilder) {
        if ((foundAt.first == -1 && foundAt.second == -1) || parentMatrix[foundAt.first][foundAt.second].first.equals(SENTINEL)) {
            return stringBuilder.reverse().toString();
        }
        stringBuilder.append(parentMatrix[foundAt.first][foundAt.second].second);
        foundAt = parentMatrix[foundAt.first][foundAt.second].first;
        return makeThePath(stringBuilder);
    }

    private String bfs() {
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            for (int k = 0; k < dir.length; k++) {
                int ni = pair.first + dir[k][0];
                int nj = pair.second + dir[k][1];
                if (isValid(ni, nj)) {
                    parentMatrix[ni][nj] = Pair.makePair(pair, move[k]);
                    if (matrix[ni][nj] == 'B') {
                        foundAt = Pair.makePair(ni, nj);
                        return "YES";
                    }
                    queue.add(Pair.makePair(ni, nj));
                    visited.add(Pair.makePair(ni, nj));
                }
            }
        }
        return "NO";
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < m && matrix[i][j] != '#' && !visited.contains(Pair.makePair(i, j));
    }
}
