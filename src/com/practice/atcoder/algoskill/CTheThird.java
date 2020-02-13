package com.practice.atcoder.algoskill;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class CTheThird {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);
        out.println(arr[3]);
    }
}
