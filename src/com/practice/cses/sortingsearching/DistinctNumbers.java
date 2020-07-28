package com.practice.cses.sortingsearching;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DistinctNumbers {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        int count = 0;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] != prev) {
                prev = arr[i];
                count++;
            }
        }

        out.println(count);
    }
}
