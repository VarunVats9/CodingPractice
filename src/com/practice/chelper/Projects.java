package com.practice.chelper;

import com.practice.egork.lib.search.LowerBound;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Projects {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Project[] p = new Project[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Project(in.nextInt(), in.nextInt(), in.nextInt());
        }

        Arrays.sort(p, (p1, p2) -> {
            if (p1.e > p2.e) return 1;
            else if (p1.e == p2.e) return 0;
            return -1;
        });

        int[] e = new int[n];

        for (int i = 0; i < n; i++) e[i] = p[i].e;

        long[] dp = new long[n+1];

        dp[0] = p[0].r;
        for (int i = 1; i < n; i++) {
            int target = p[i].s;
            int j = LowerBound.lowerBound(e, n, target) - 1;
            dp[i] = Math.max(dp[i-1], p[i].r + (j < 0 ? 0 : dp[j]));
        }

        out.println(dp[n-1]);
    }

    public class Project {
        public int s, e, r;

        public Project(int s, int e, int r) {
            this.s = s;
            this.e = e;
            this.r = r;
        }
    }
}
