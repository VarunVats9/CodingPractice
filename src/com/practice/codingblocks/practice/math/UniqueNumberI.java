package com.practice.codingblocks.practice.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UniqueNumberI {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            Integer n = Integer.parseInt(line);
            line = bufferedReader.readLine();
            String num[] = line.split(" ");
            int x = Integer.parseInt(num[0]);
            for (int i = 1; i < num.length; i++) {
                x = x ^ Integer.parseInt(num[i]);
            }
            System.out.println(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
