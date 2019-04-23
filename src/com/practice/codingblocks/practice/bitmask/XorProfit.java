package com.practice.codingblocks.practice.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class XorProfit {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int x = Integer.parseInt(line);
            line = bufferedReader.readLine();
            int y = Integer.parseInt(line);

            int max = Integer.MIN_VALUE;

            for (int i = x; i <= y; i++) {
                for (int j = i+1; j <= y; j++) {
                    max = Math.max(max, i^y);
                }
            }

            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
