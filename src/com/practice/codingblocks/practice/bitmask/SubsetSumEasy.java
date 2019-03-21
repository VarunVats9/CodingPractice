package com.practice.codingblocks.practice.bitmask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubsetSumEasy {

    private static int[] arr = new int[4];

    private static boolean subsetSum(int[] arr, int n) {
        for (int i = 1; i <= (1<<n) - 1; i++) {
            int sum = 0;
            for (int j = 0; j <= n-1; j++) {
                if (((1<<j) & i) > 0) {
                    sum += arr[j];
                }
            }
            if (sum == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            while (t-- > 0) {
                line = bufferedReader.readLine();
                int n = Integer.parseInt(line);
                line = bufferedReader.readLine();
                String[] str = line.trim().split("\\s+");
                for (int i = 0; i < str.length; i++) {
                    arr[i] = Integer.parseInt(str[i]);
                }
                System.out.println(subsetSum(arr, n) ? "Yes" : "No");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
