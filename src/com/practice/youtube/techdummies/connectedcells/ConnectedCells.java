package com.practice.youtube.techdummies.connectedcells;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class ConnectedCells {

    private static final String SEPARATOR = "--------------------------------------------------------------------";

    private static final int[][] DIRECTIONS = new int[][]{
            {0, 1},     // E
            {1, 0},     // S
            {0, -1},    // W
            {-1, 0},    // N
            {1, 1},     // S-E
            {-1, 1},    // N-E
            {1, -1},    // N-W
            {-1, -1}};  // S-W

    private static final Comparator<Pair> PAIR_COMPARATOR = new Comparator<Pair>() {
        @Override
        public int compare(final Pair p1, final Pair p2) {
            final int comp = p1.row.compareTo(p2.row);
            if (comp == 0) {
                return p1.col.compareTo(p2.col);
            }
            return comp;
        }
    };
    private static final int START = 0;

    public static void main(String[] args) {

        // Setup the matrix.
        int[][] matrix = new int[][]{
                {1, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 1}};

        // Approach 1 : Using Stack.
        {
            System.out.println("************** STACK APPROACH ************* \n");

            final long stackApproachStart = System.nanoTime();

            Stack<Pair> stack = new Stack<>();
            Set<Pair> visited = new HashSet<>();
            TreeSet<Pair> islands = new TreeSet<>(PAIR_COMPARATOR);

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    final Pair pair = new Pair(i, j);
                    islands.clear();
                    stack.clear();
                    if (matrix[i][j] == 1 && !visited.contains(pair)) {
                        stack.add(pair);
                        addNeighboursInStack(islands, stack, matrix);
                        System.out.println(islands);
                        visited.addAll(islands);
                    }
                }
            }

            System.out.println("\nTotal time taken by STACK approach : [" + (System.nanoTime() - stackApproachStart) / 1000 + "] us\n");
        }

        System.out.println(SEPARATOR);

        // Approach 2 : Using Dfs.
        {
            System.out.println("\n************** DFS APPROACH ************* \n");

            final long dfsApproachStart = System.nanoTime();

            Set<Pair> visited = new HashSet<>();
            TreeSet<Pair> islands = new TreeSet<>(PAIR_COMPARATOR);

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    final Pair pair = new Pair(i, j);
                    islands.clear();
                    if (matrix[i][j] == 1 && !visited.contains(pair)) {
                        dfsInMatrix(islands, pair, matrix);
                        System.out.println(islands);
                        visited.addAll(islands);
                    }
                }
            }

            System.out.println("\nTotal time taken by DFS approach : [" + (System.nanoTime() - dfsApproachStart) / 1000 + "] us");
        }
    }

    private static void dfsInMatrix(final TreeSet<Pair> islands, final Pair pair, final int[][] matrix) {

        if (islands.contains(pair)) {
            return;
        }

        islands.add(pair);

        for (int i = START; i < DIRECTIONS.length; i++) {
            int x = pair.row + DIRECTIONS[i][0];
            int y = pair.col + DIRECTIONS[i][1];
            final Pair neighbour = new Pair(x, y);
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != 1 || islands.contains(new Pair(x, y))) {
                continue;
            }
            dfsInMatrix(islands, neighbour, matrix);
        }
    }

    private static void addNeighboursInStack(final Set<Pair> islands, final Stack<Pair> stack, final int[][] matrix) {

        while (!stack.isEmpty()) {
            final Pair pair = stack.pop();
            if (islands.contains(pair)) {
                continue;
            }
            for (int i = START; i < DIRECTIONS.length; i++) {
                int x = pair.row + DIRECTIONS[i][0];
                int y = pair.col + DIRECTIONS[i][1];
                final Pair neighbour = new Pair(x, y);
                if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != 1 || islands.contains(new Pair(x, y))) {
                    continue;
                }
                stack.add(neighbour);
            }
            islands.add(pair);
        }
    }

    private static class Pair {

        private Integer row;
        private Integer col;

        public Pair(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Pair pair = (Pair)o;
            return row == pair.row &&
                    col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder()
                    .append("[").append(row)
                    .append(",")
                    .append(col).append("]");
            return sb.toString();
        }
    }

}
