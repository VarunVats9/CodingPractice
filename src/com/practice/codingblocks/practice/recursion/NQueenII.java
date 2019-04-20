package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;

public class NQueenII {

    private static ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>> result = new ArrayList<>();
    private static int[][] chess;
    private static int[] row, col;
    private static int[] dg, adg;
    private static int T;

    private static void findQueenPlacement(int r, int c, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> med) {

        AbstractMap.SimpleEntry<Integer, Integer> entry = null;

        // set values.
        if (r >= 0 && c >= 0) {
            setValue(r, c, 1);
            entry = new AbstractMap.SimpleEntry<>(r, c);
            med.add(entry);
        }

        // base case
        if (r >= T) {
            result.add(new ArrayList<>(med));
            setValue(r, c, 0);
            med.remove(entry);
            return;
        }

        for (int i = 0; i <= T; i++) {
            if ((r == -1 && c == -1) || (chess[r+1][i] == 0 && row[r+1] == 0 && col[i] == 0 && adg[r+1+i] == 0 && dg[T+r+1-i] == 0)) {
                findQueenPlacement(r+1, i, med);
            }
        }

        // reset values.
        if (r >= 0 && c >= 0) {
            setValue(r, c, 0);
            med.remove(entry);
        }
    }

    private static void setValue(int r, int c, int value) {
        row[r] = value;
        col[c] = value;
        adg[r + c] = value;
        dg[T + r - c] = value;
        chess[r][c] = value;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            chess = new int[n][n];
            row = new int[n];
            col = new int[n];
            T = n-1;
            adg = new int[2*T+1];
            dg = new int[2*T+1];
            result.clear();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> med = new ArrayList<>();
            findQueenPlacement(-1, -1, med);
            System.out.println("Total solutions : " + result.size());
            printResult();
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
