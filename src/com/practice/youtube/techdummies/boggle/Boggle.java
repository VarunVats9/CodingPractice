package com.practice.youtube.techdummies.boggle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Boggle {

    private static final int[][] DIRECTIONS = new int[][]{
            {0, 1},     // E
            {1, 0},     // S
            {0, -1},    // W
            {-1, 0},    // N
            {1, 1},     // S-E
            {-1, 1},    // N-E
            {1, -1},    // N-W
            {-1, -1}};  // S-W

    private static final int START = 0;

    private static final String EMPTY = "";

    private static Set<String> wordsFound = new HashSet<>();

    public static void main(String[] args) {

        // Setup dictionary.
        String[] dictionary = new String[] {"CAT", "DOG", "BYTE", "TUBE", "CAN", "ANT", "CAR", "TANK"};

        // Initialize trie.
        final Trie trie = new Trie();

        // Populate trie.
        for (int i = 0; i < dictionary.length; i++) {
            trie.addWord(dictionary[i]);
        }

        // Setup matrix.
        char[][] matrix = new char[][] {
                {'C', 'J', 'Z', 'E'},
                {'V', 'A', 'X', 'B'},
                {'X', 'N', 'T', 'U'},
                {'I', 'A', 'N', 'K'}};

        Set<Pair> visited = new HashSet<>();

        // Start with every word, and check what all words can be formed.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                final Pair start = new Pair(i, j);
                visited.clear();
                dfsOnMatrix(start, matrix, visited, trie, matrix[i][j] + EMPTY);
            }
        }

        // Print the result.
        printResult(matrix, dictionary, wordsFound);
    }

    private static void dfsOnMatrix(final Pair start, final char[][] matrix, final Set<Pair> visited, final Trie trie, final String prefix) {

        if (visited.contains(start)) {
            return;
        }
        visited.add(start);

        if (trie.isTheEndWord(prefix)) {
            wordsFound.add(prefix);
        }

        for (int i = START; i < DIRECTIONS.length; i++) {
            int x = start.row + DIRECTIONS[i][0];
            int y = start.col + DIRECTIONS[i][1];
            final Pair neighbour = new Pair(x, y);
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited.contains(neighbour) || trie.doesNotExist(prefix + matrix[x][y])) {
                continue;
            }
            dfsOnMatrix(neighbour, matrix, visited, trie, prefix + matrix[x][y]);
        }
    }

    private static class Pair {

        private int row;
        private int col;

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
    }

    private static void printResult(final char[][] matrix, final String[] dictionary, final Set<String> wordsFound) {

        System.out.println("Dictionary : " + Arrays.toString(dictionary));

        final StringBuilder stringBuilder = new StringBuilder(EMPTY).append("\nBOGGLE\n");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                stringBuilder.append(matrix[i][j]).append("     ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());

        System.out.println("Dictionary words found in the boggle : " + wordsFound);
    }

}
