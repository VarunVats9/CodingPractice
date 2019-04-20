package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NKnight {

    private static ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>> result = new ArrayList<>();
    private static Set<ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>> set = new HashSet<>();
    private static int[][] chess;
    private static int T;
    private static int count = 0;
    private static int[][] dir = new int[][]{{-2, 1}, {-2, -1}, {2, 1}, {2, -1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};

    private static void findKnightPlacement(int r, int c, Set<AbstractMap.SimpleEntry<Integer, Integer>> notAllowed, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> med) {

        AbstractMap.SimpleEntry<Integer, Integer> entry = null;

        // set values.
        if (r >= 0 && c >= 0) {
            chess[r][c] = 1;
            count++;
            entry = new AbstractMap.SimpleEntry<>(r, c);
            med.add(entry);
            fillNotAllowedPositions(notAllowed, r, c);
        }

        // base case
        if (count == T+1) {
            med.sort((a, b) -> {
                if (a.getKey().equals(b.getKey())) {
                    return a.getValue().compareTo(b.getValue());
                }
                return a.getKey().compareTo(b.getKey());
            });

            if (!set.contains(med)) {
                result.add(new ArrayList<>(med));
                set.add(new ArrayList<>(med));
            }
            chess[r][c] = 0;
            count--;
            med.remove(entry);
            revertNotAllowedPositions(notAllowed, r, c);
            return;
        }

        for (int i = 0; i <= T; i++) {
            for (int j = 0; j <= T; j++) {
                if (chess[i][j] == 0 && !notAllowed.contains(new AbstractMap.SimpleEntry<>(i, j)) && currentAllowed(i, j)) {
                    findKnightPlacement(i, j, notAllowed, med);
                }
            }
        }

        // reset values.
        if (r >= 0 && c >= 0) {
            chess[r][c] = 0;
            count--;
            med.remove(entry);
            revertNotAllowedPositions(notAllowed, r, c);
        }
    }

    private static boolean currentAllowed(int r, int c) {
        for (int i = 0; i < dir.length; i++) {
            int x = r + dir[i][0];
            int y = c + dir[i][1];
            if (x >= 0 && y >= 0 && x <= T && y <= T && chess[x][y] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void revertNotAllowedPositions(Set<AbstractMap.SimpleEntry<Integer, Integer>> notAllowed, int r, int c) {
        for (int i = 0; i < dir.length; i++) {
            int x = r + dir[i][0];
            int y = c + dir[i][1];
            if (x >= 0 && y >= 0 && x <= T && y <= T) {
                notAllowed.remove(new AbstractMap.SimpleEntry<>(x, y));
            }
        }
    }

    private static void fillNotAllowedPositions(Set<AbstractMap.SimpleEntry<Integer, Integer>> notAllowed, int r, int c) {
        for (int i = 0; i < dir.length; i++) {
            int x = r + dir[i][0];
            int y = c + dir[i][1];
            if (x >= 0 && y >= 0 && x <= T && y <= T) {
                notAllowed.add(new AbstractMap.SimpleEntry<>(x, y));
            }
        }
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            chess = new int[n][n];
            T = n-1;
            result.clear();
            set.clear();
            Set<AbstractMap.SimpleEntry<Integer, Integer>> notAllowed = new HashSet<>();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> med = new ArrayList<>();
            findKnightPlacement(-1, -1, notAllowed, med);
            printResult();
            System.out.print("\n" + result.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResult() {
        result.forEach(row -> {
            row.forEach(col -> {
                System.out.print("{" + col.getKey() + "-" + col.getValue() + "}");
                System.out.print(" ");
            });
            System.out.print(" ");
        });
    }
}
