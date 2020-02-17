package com.practice.atcoder.algoskill;

import java.util.*;
import java.io.PrintWriter;

public class IProcurement {

    public static final long LONG_MAX = 1L << 50;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        in.nextLine();
        List<Pair> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            String s = in.nextLine();
            String[] token = s.split(" ");
            int x = 0;
            for (int j = n-1, k = 0; j >= 0; j--, k++) {
                x |= token[0].charAt(j) == 'Y' ? 1 << k : 0;
            }
            list.add(new Pair(x, Integer.parseInt(token[1])));
        }

        long[] dp = new long[1 << n];
        Arrays.fill(dp, LONG_MAX);
        dp[0] = 0;

        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < m; j++) {
                dp[list.get(j).first | i] = Math.min(dp[list.get(j).first | i], dp[i] + list.get(j).second);
            }
        }

        out.println(dp[(1 << n) - 1] == LONG_MAX ? -1 : dp[(1 << n) - 1]);
    }

    public static class Pair {

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
