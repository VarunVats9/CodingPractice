package com.practice.skiena.ch1;

import java.util.Scanner;
import java.io.PrintWriter;

public class The3n1Problem {

    static int[] arr = new int[1000005];

    {
        for (int i = 1; i < arr.length; i++) {
            int counter = 1;
            long k = i;
            while (k != 1) {
                assert k >= 0 : "K can never be negative";
                if (k >= arr.length || arr[(int)k] == 0) {
                    if (k % 2 > 0) {
                        k = 3 * k + 1;
                    } else {
                        k = k / 2;
                    }
                } else  {
                    counter += arr[(int)k] - 1;
                    break;
                }
                counter++;
            }
            arr[i] = counter;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext()) {
            int i = in.nextInt();
            int j = in.nextInt();

            int max_loop = 0;
            for (int k = Math.min(i, j); k <= Math.max(i, j); k++) {
                max_loop = Math.max(max_loop, arr[k]);
            }

            out.println(i + " " + j + " " + max_loop);
        }
    }
}
