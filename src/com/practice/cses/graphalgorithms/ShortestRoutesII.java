package com.practice.cses.graphalgorithms;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

/* Floyd Warshall */
public class ShortestRoutesII {

    static long[][] dist;
    static int n, m, q;
    static long INF = (long) 1e13;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        q = in.nextInt();
        dist = new long[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            a--; b--;
            dist[a][b] = Math.min(dist[a][b], c);
            dist[b][a] = Math.min(dist[a][b], c);
        }

        // pre-process
        for (int i = 0; i < n; i++) {
            // Relax the node i;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) continue;
                    if (ifGreater(dist[j][k], dist[j][i], dist[i][k])) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            long ans = dist[x][y];
            out.println(ans == INF ? -1 : ans);
        }
    }

    private boolean ifGreater(long c, long a, long b) {
        if (a == INF || b == INF)
            return false;
        return c > a + b;
    }
}
