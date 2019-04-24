package com.practice.codingblocks.practice.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarJourney {

    private static long minimumCost(int[] c, int[] l) {
        long cost = 0, currMin = c[0];

        for (int i = 0; i < c.length-1; i++) {
            if (c[i] < currMin) {
                currMin = c[i];
            }
            cost += l[i] * currMin;
        }
        return cost;
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            while (t-- > 0) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                int[] c = new int[n+1];
                int[] l = new int[n+1];
                line = bufferedReader.readLine();
                String[] str = line.split(" ");
                for (int i = 0; i < n; i++) {
                    c[i] = Integer.parseInt(str[i]);
                }
                line = bufferedReader.readLine();
                str = line.split(" ");
                for (int i = 0; i < n; i++) {
                    l[i] = Integer.parseInt(str[i]);
                }
                c[n] = l[n] = Integer.MAX_VALUE;
                System.out.println(minimumCost(c, l));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
