package com.practice.codingblocks.practice.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumTimeTraversal {

    private static int[] start = new int[2], end = new int[2];
    private static int[][] pipes;
    private static int MIN;

    private static void bestPossibleTime(int psize, int index, int x, int y) {
        if (x == end[0] && y == end[1]) {
            return;
        }

        for (int i = index; i < psize; i++) {
            //bestPossibleTime(psize, i, )
        }

    }

    private static int distance(int x, int y, int x1, int y1) {
        return Math.abs(x1-x) + Math.abs(y1-y);
    }

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            while (t-- > 0) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                pipes = new int[n][5];
                MIN = Integer.MAX_VALUE;
                line = bufferedReader.readLine();
                String[] str = line.trim().split("\\s+");
                start[0] = Integer.parseInt(str[0]);
                start[1] = Integer.parseInt(str[1]);
                end[0] = Integer.parseInt(str[2]);
                end[1] = Integer.parseInt(str[3]);
                int j = 0;
                while (j < n) {
                    line = bufferedReader.readLine();
                    str = line.trim().split("\\s+");
                    for (int i = 0; i < 5; i++) {
                        pipes[j][i] = Integer.parseInt(str[i]);
                    }
                    //bestPossibleTime(pipes, n)
                    System.out.println(MIN);
                    j++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
