package com.practice.cses.introductoryproblems;

import java.io.PrintWriter;
import java.util.Scanner;

public class MissingNumber {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextInt();

        long sum = 0;
        for (int i = 0; i < n-1; i++) sum += in.nextInt();

        out.println((n*(n+1))/2 - sum);
    }
}
