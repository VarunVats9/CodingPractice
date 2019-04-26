package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinaryStrings {

    private static long noConsecutiveOne(int num) {
        long[] zero = new long[num+1];
        long[] ones = new long[num+1];

        zero[1] = ones[1] = 1;
        for (int i = 2; i <= num; i++) {
            zero[i] = zero[i-1] + ones[i-1];
            ones[i] = zero[i-1];
        }

        return zero[num] + ones[num];
    }

    public static void main(String[] args){

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int i = 1; i <= t; i++) {
                line = bufferedReader.readLine();
                int num = Integer.parseInt(line);
                System.out.println(noConsecutiveOne(num));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
