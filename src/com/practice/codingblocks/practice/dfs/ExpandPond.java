package com.practice.codingblocks.practice.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpandPond {

    private static int count = 0, result = 0;
    private static int[][] temp, matrix;
    private static int groupId = 0;
    private static Map<Integer, Integer> map = new HashMap<>();
    private static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void dfs(int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] == 0 || matrix[i][j] == -1) {
            return;
        }

        count++;
        temp[i][j] = groupId;
        matrix[i][j] = -1;

        dfs( i+1, j);
        dfs( i-1, j);
        dfs( i, j+1);
        dfs( i, j-1);
    }

    private static void solveDfs() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    groupId++;
                    count = 0;
                    dfs(i, j);
                    map.put(groupId, count);
                    result = Math.max(result, count);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    count = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < dir.length; k++) {
                        int x = i + dir[k][0];
                        int y = j + dir[k][1];
                        if (valid(x, y) && !set.contains(getGroup(x, y))) {
                            count += map.get(getGroup(x, y));
                            set.add(getGroup(x, y));
                        }
                    }
                    if (count > result) {
                        result = count;
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static Integer getGroup(int i, int j) {
        return temp[i][j];
    }

    private static boolean valid(int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String mn[] = line.split(" ");
            int m = Integer.parseInt(mn[0]);
            int n = Integer.parseInt(mn[1]);

            matrix = new int[m][n];
            temp = new int[m][n];

            for (int i = 0; i < m; i++) {
                line = bufferedReader.readLine();
                String row[] = line.split(" ");
                for (int j = 0; j < row.length; j++) {
                    matrix[i][j] = Integer.parseInt(row[j]);
                }
            }

            solveDfs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
