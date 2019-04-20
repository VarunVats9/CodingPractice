package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RatMaze {

    private static char[][] maze;
    private static int[][] result;
    private static boolean found = false;

    private static void findPath(int r, int c, int x, int y) {
        // base case
        if (found) {
            return;
        }

        // set value
        result[x][y] = 1;

        if (r-1 == x && c-1 == y) {
            found = true;
            return;
        }

        // check if possible to move right
        if (y+1 < c && maze[x][y+1] != 'X' && !found) {
            findPath(r, c, x, y+1);
        }

        if (x+1 < r && maze[x+1][y] != 'X' && !found) {
            findPath(r, c, x+1, y);
        }

        // unset value
        if (!found) {
            result[x][y] = 0;
        }
    }

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String[] str = line.split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            maze = new char[n][m];
            result = new int[n][m];
            for (int i = 0; i < n; i++) {
                line = bufferedReader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    maze[i][j] = line.charAt(j);
                }
            }
            findPath(n, m, 0, 0);
            printResult();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResult() {
        if (!found) {
            System.out.println("-1");
            return;
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
