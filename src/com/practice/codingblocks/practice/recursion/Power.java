package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Power {

    private static long power(long n, long p) {
        // base case
        if (p == 1) return n;
        // recursive call
        long ans = power(n, p/2);

        if ((p & 1) == 1) {
            return ans * ans * n;
        }
        return ans * ans;
    }

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            long n = Long.parseLong(line);
            line = bufferedReader.readLine();
            long p = Long.parseLong(line);
            System.out.println(power(n, p));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
