package com.practice.atcoder.abc152;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.io.PrintWriter;

public class DHandstand2 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        HashMap<Pair, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            Pair p = f(i);
            Integer count = map.putIfAbsent(p, 1);
            if (count != null) {
                map.put(p, count + 1);
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            Pair p = f(i);
            Pair q = new Pair(p.second, p.first);
            ans += map.getOrDefault(q, 0);
        }

        out.println(ans);
    }

    public Pair f(int x) {
        int b = x % 10;
        int a = 0;

        while (x > 0) {
            a = x;
            x /= 10;
        }

        return new Pair(a, b);
    }


    private static class Pair {

        public int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first &&
                    second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
