package com.practice.cses.graphalgorithms;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

/* Bellman Ford */
public class HighScore {

    static Edge[] edges;
    static long[] dist;
    static int n, m;
    static long INF = (long) (1e14);
    static long NINF = (-1 * INF);

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        edges = new Edge[m];
        dist = new long[n];

        for (int i = 0; i < m; i++) edges[i] = new Edge();
        Arrays.fill(dist, INF);

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int w = in.nextInt();
            x--; y--;
            edges[i].u = x;
            edges[i].v = y;
            edges[i].w = -1 * w;
        }

        dist[0] = 0;
        propagationPhase();
        negativeCycleResolutionPhase();

        if (dist[n-1] == NINF) {
            out.println(-1);
        } else {
            out.println(dist[n-1] * -1);
        }
    }

    private void propagationPhase() {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[edges[j].u] == INF) continue;
                dist[edges[j].v] = Math.min(dist[edges[j].v], dist[edges[j].u] + edges[j].w);
            }
        }
    }

    private void negativeCycleResolutionPhase() {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[edges[j].u] == INF) continue;
                // At this stage, we should not get better path, so should be a Neg Cycle
                if (dist[edges[j].v] > dist[edges[j].u] + edges[j].w) {
                    dist[edges[j].v] = NINF;
                }
            }
        }
    }

    private static class Edge {
        int u, v, w;
    }
}
