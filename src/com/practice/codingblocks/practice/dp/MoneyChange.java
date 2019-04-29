package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MoneyChange {

    private static int mod = 1000000007;
    private static int[] arr;
    private static int[][] dp;
    private static int amt;

    private static int dpMoneyChange(int idx, int amt) {
        if (amt == 0) {
            return 1;
        }

        if (idx == arr.length) {
            return 0;
        }

        if (dp[idx][amt] != -1)
            return dp[idx][amt];

        return dp[idx][amt] = amt >= arr[idx] ? (dpMoneyChange(idx+1, amt) % mod +
                dpMoneyChange(idx, amt - arr[idx]) % mod) % mod : 0;
    }


    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                arr = new int[n];
                line = bufferedReader.readLine();
                String str[] = line.split(" ");
                for (int j = 0; j < n; j++) {
                    arr[j] = Integer.parseInt(str[j]);
                }
                Arrays.sort(arr);
                line = bufferedReader.readLine();
                amt = Integer.parseInt(line);
                dp = new int[n][amt+1];
                for (int[] row : dp)
                    Arrays.fill(row, -1);
                System.out.println(dpMoneyChange(0, amt));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
