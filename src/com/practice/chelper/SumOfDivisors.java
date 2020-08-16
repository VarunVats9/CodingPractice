package com.practice.chelper;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class SumOfDivisors {

    static private int MAX = 1_000_005;
    static private int[] prime;
    static private int ans = 1;

    static {
        prime = new int[MAX];
        int sqr = (int) Math.sqrt(MAX);
        prime[0] = prime[1] = 0;

        for (int i = 2; i * i <= MAX; i++) {
            if (prime[i] == 0) {
                for (int j = i*i; j < MAX; j = j+i) {
                    prime[j] = i;
                }
            }
        }

        for (int i = 2; i < MAX; i++) {
            if (prime[i] == 0) prime[i] = i;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int x = in.nextInt();

    }
}
