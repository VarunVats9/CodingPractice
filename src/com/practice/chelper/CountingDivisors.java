package com.practice.chelper;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CountingDivisors {

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
        int t = in.nextInt();
        while (t-- > 0) {
            int x = in.nextInt();
            ans = 1;
            Map<Integer, Integer> map = new HashMap<>();
            while (x > 1) {
                map.putIfAbsent(prime[x], 0);
                map.put(prime[x], map.get(prime[x]) + 1);
                x /= prime[x];
            }
            map.forEach((k, v) -> ans *= v+1);
            out.println(ans);
        }
    }
}
