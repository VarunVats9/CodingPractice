package com.practice.codejam19.round1A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pylons {

    private static List<Pair<Integer, Integer>> list = new ArrayList<>();
    private static List<Pair<Integer, Integer>> result = new ArrayList<>();
    private static int r, c;

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int j = 1; j <= t; j++) {
                line = bufferedReader.readLine();
                String[] str = line.split(" ");
                r = Integer.parseInt(str[0]);
                c = Integer.parseInt(str[1]);
                list.clear();
                result.clear();
                jump(r, c);
                if (result.size() == r*c) {
                    System.out.println("Case #" + j + ": " + "POSSIBLE");
                    printSequence(result);
                } else {
                    System.out.println("Case #" + j + ": " + "IMPOSSIBLE");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jump(final int r, final int c) {
        int[][] matrix  = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                solve(matrix, i, j);
            }
        }
    }

    private static void printSequence(final List<Pair<Integer, Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).left + " " + list.get(i).right);
        }
    }

    private static void solve(final int[][] matrix, final int x, final int y) {
        matrix[x][y] = 1;
        System.out.println("x :" + x + "y :" + y);
        Pair<Integer, Integer> pair = new Pair<>(x+1, y+1);
        list.add(pair);
        if (list.size() == r*c && result.size() == 0) {
            result.addAll(list);
            return;
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0 && !notValidCell(i, j, x, y)) {
                    solve(matrix, i, j);
                }
            }
        }

        list.remove(pair);
        matrix[x][y] = 0;
    }

    private static boolean notValidCell(final int i, final int j, final int x, final int y) {
        return i == x || j == y || (i+j) == (x+y) || (i-j) == (x-y);
    }

    private static class Pair<L, R> {

        private L left;
        private R right;

        public Pair(final L left, final R right) {
            this.left = left;
            this.right = right;
        }

        public Pair(final Pair<L, R> pair) {
            this.setLeft(pair.left);
            this.setRight(pair.right);
        }

        public L getLeft() {
            return this.left;
        }

        public R getRight() {
            return this.right;
        }

        public void setLeft(final L left) {
            this.left = left;
        }

        public void setRight(final R right) {
            this.right = right;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Pair<?, ?> pair = (Pair<?, ?>)o;
            return Objects.equals(left, pair.left) &&
                    Objects.equals(right, pair.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append(left);
            sb.append(", ").append(right);
            sb.append('}');
            return sb.toString();
        }
    }
}
