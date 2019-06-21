package com.practice.codingblocks.practice.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SudokuSolver {

    static int sudoku_size;
    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            int[][] matrix = new int[n][n];
            sudoku_size = n;
            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                String[] row = line.split(" ");
                for (int j = 0; j < row.length; j++) {
                    int read = Integer.parseInt(row[j]);
                    matrix[i][j] = read;
                }
            }
            if(solve(matrix, 0, 0)) {
                print(matrix);
            } else {
                System.out.println("Solution doesn't exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < sudoku_size; i++) {
            for (int j = 0; j < sudoku_size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean solve(int[][] matrix, int row, int col) {
        if (row == sudoku_size) {
            return true;
        }
        if (col == sudoku_size) {
            return solve(matrix, row+1, 0);
        }
        for (int num = 1; num <= sudoku_size; num++) {
            if (matrix[row][col] != 0) {
                return solve(matrix, row, col+1);
            }
            if (canPlace(matrix, row, col, num)) {
               matrix[row][col] = num;
               boolean ans = solve(matrix, row, col+1);
               if (ans) return true;
               matrix[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean canPlace(int[][] matrix, int row, int col, int num) {
        for (int i = 0; i < sudoku_size; i++) {
            if (matrix[i][col] == num) return false;
        }
        for (int i = 0; i < sudoku_size; i++) {
            if (matrix[row][i] == num) return false;
        }
        int sq = (int)Math.sqrt(sudoku_size);
        int row_block = row / sq;
        int col_block = col / sq;
        for (int i = sq * row_block; i < sq * (row_block + 1); i++) {
            for (int j = sq * col_block; j < sq * (col_block + 1); j++) {
                if (matrix[i][j] == num) return false;
            }
        }
        return true;
    }
}
