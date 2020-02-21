package com.practice.atcoder.educationaldp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class GLongestPath {

    public static long[] dp;
    public static int[] inDegree;
    private static boolean[] visited;
    private static ArrayList<Integer>[] edges;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        inDegree = new int[n+1];
        visited = new boolean[n+1];

        edges = new ArrayList[n+1];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            edges[x].add(y);
            inDegree[y]++;
        }

        // dp[i] -> This holds the longest distance to reach from some node to 'i'.
        dp = new long[n+1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && inDegree[i] == 0) {
                dfs(i);
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[i]);
        }

        out.println(ans);
    }

    private void dfs(int i) {
        visited[i] = true;
        for (Integer e : edges[i]) {
            dp[e] = Math.max(dp[e], 1 + dp[i]);
            inDegree[e]--;
            if (inDegree[e] == 0) {
                dfs(e);
            }
        }
    }
}
