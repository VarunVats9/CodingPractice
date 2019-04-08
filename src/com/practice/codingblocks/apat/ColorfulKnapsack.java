package com.practice.codingblocks.apat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class ColorfulKnapsack {

    private static int max = Integer.MIN_VALUE;

    private static void knapsack(int[] w, int[] c, int x, int m) {
        HashSet<Integer> set = new HashSet<>();
        knapsackUtil(w, c, x, 0, set, m);
    }

    private static void knapsackUtil(final int[] w, final int[] c, final int x, final int idx, final HashSet<Integer> set, final int m) {
        if (set.size() == m) {
            //return m;
        }
    }

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            String[] str = line.trim().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int x = Integer.parseInt(str[2]);
            line = bufferedReader.readLine();
            str = line.trim().split(" ");
            int[] w = new int[n];
            for (int i = 0; i < str.length; i++) {
                w[i] = Integer.parseInt(str[i]);
            }
            line = bufferedReader.readLine();
            str = line.trim().split(" ");
            int[] c = new int[n];
            for (int i = 0; i < str.length; i++) {
                c[i] = Integer.parseInt(str[i]);
            }
            knapsack(w, c, x, m);
            System.out.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
