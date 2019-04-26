package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StockSelling {

    private static int[] arr;
    private static int[][] dp;

    private static int recursiveSelling(int[] arr, int k, int idx, int profit, boolean buy) {
        if (idx == arr.length || k == 0) {
            return profit;
        }

        return buy
                ? Math.max(recursiveSelling(arr, k, idx+1, profit - arr[idx], false), recursiveSelling(arr, k, idx+1, profit, true))
                : Math.max(recursiveSelling(arr, k-1, idx+1, profit + arr[idx], true), recursiveSelling(arr, k, idx+1, profit, false));
    }

    private static int dpSelling(int k, int idx) {
        if (idx == arr.length || k == 0) {
            return 0;
        }

        if (dp[k][idx] != -1) {
            return dp[k][idx];
        }

        return dp[k][idx] = (k & 1) == 0
                ? Math.max(dpSelling(k-1, idx+1) - arr[idx], dpSelling(k, idx+1))
                : Math.max(dpSelling(k-1, idx+1) + arr[idx], dpSelling(k, idx+1));
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                String[] str = line.split(" ");
                int k = Integer.parseInt(str[0]);
                int n = Integer.parseInt(str[1]);
                arr = new int[n];
                dp = new int[2*k+1][n];
                for (int[] row : dp) {
                    Arrays.fill(row, -1);
                }
                for (int j = 0; j < n; j++) {
                    arr[j] = Integer.parseInt(str[j+2]);
                }
                System.out.println(recursiveSelling(arr, k, 0, 0, true));
                System.out.println(dpSelling(2*k,0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
