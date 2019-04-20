package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClassAssignment {

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            int[] dp = new int[45];
            dp[1] = 2;
            dp[2] = 3;
            for (int i = 3; i <= 44; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                System.out.println("#" + i + " : " + dp[n]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
