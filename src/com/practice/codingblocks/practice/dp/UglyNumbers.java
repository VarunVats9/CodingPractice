package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UglyNumbers {

    private static int[] dp;

    private static int dpUgly(int i) {
        return 0;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                System.out.println(dpUgly(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
