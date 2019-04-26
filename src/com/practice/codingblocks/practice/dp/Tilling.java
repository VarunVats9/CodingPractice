package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tilling {

    private static int mod = 1000000007;

    private static long tilling(int n, int m) {

        if (m == 1) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] =(dp[i-1] % mod + (i-m >= 0 ? dp[i-m] : 0) % mod) % mod;
        }
        return dp[n];
    }

    public static void main(String[] args){

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                String[] str = line.split(" ");
                int n = Integer.parseInt(str[0]);
                int m = Integer.parseInt(str[1]);
                System.out.println(tilling(n, m));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
