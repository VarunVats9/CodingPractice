package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Knapsack01 {

    private static int N, S;
    private static int[] c, s;
    private static int[][] dp;

    private static int recursiveKnapsack(int idx, int S) {
        if (idx == s.length || S <= 0) {
            return 0;
        }

        return s[idx] <= S
                ? Math.max(recursiveKnapsack(idx+1, S - s[idx]) + c[idx], recursiveKnapsack(idx+1, S))
                : recursiveKnapsack(idx+1, S);
    }

    private static int dpKnapsack(int idx, int S) {
        if (idx == s.length || S <= 0) {
            return 0;
        }

        if (dp[idx][S] != -1) {
            return dp[idx][S];
        }

        return dp[idx][S] = s[idx] <= S
                ? Math.max(recursiveKnapsack(idx+1, S - s[idx]) + c[idx], recursiveKnapsack(idx+1, S))
                : recursiveKnapsack(idx+1, S);
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String[] str = line.split(" ");
            N = Integer.parseInt(str[0]);
            S = Integer.parseInt(str[1]);
            c = new int[N];
            s = new int[N];
            dp = new int[N][S+1];
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }
            line = bufferedReader.readLine();
            str = line.split(" ");
            for (int i = 0; i < N; i++) {
                s[i] = Integer.parseInt(str[i]);
            }
            line = bufferedReader.readLine();
            str = line.split(" ");
            for (int i = 0; i < N; i++) {
                c[i] = Integer.parseInt(str[i]);
            }
            System.out.println(recursiveKnapsack(0, S));
            System.out.println(dpKnapsack(0, S));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
