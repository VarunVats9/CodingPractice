package com.practice.cses.graphalgorithms;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

/*
* 2) How dist matches to current_dist, line 66. Think on graph.
* */

public class ShortestRoutesI {

    static boolean[] visited;
    static Node[] nodes;
    static PriorityQueue<Pair<Integer, Long>> queue;
    static long[] dist;
    static List<Integer> path;
    static int n, m;
    static long INF = Long.MAX_VALUE;
    
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        nodes = new Node[n];
        visited = new boolean[n];
        dist = new long[n];
        path = new ArrayList<>();
        queue = new PriorityQueue<>(Comparator.comparing(a -> a.second));

        for (int i = 0; i < n; i++) nodes[i] = new Node();
        Arrays.fill(dist, INF);

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int w = in.nextInt();
            x--; y--;
            nodes[x].adj.add(Pair.makePair(y, w));
        }

        sssp(0);

        for (int i = 0; i < n; i++) {
            out.print(dist[i] + " ");
        }
        out.println();
    }

    private void sssp(int s) {
        dist[s] = 0;
        queue.add(Pair.makePair(0, 0L));
        while (!queue.isEmpty()) {
            Pair<Integer, Long> pair = queue.poll();
            visited[s] = true;
            Integer n = pair.first;
            Long currentDist = pair.second;
            if (dist[n] < currentDist) continue;
            ArrayList<Pair<Integer, Integer>> adj = nodes[n].adj;
            for (int i = 0; i < adj.size(); i++) {
                Integer neigh = adj.get(i).first;
                Integer neighDist = adj.get(i).second;
                if (!visited[neigh]) {
                    if (dist[neigh] > currentDist + neighDist) {
                        dist[neigh] = currentDist + neighDist;
                        queue.add(Pair.makePair(neigh, currentDist + neighDist));
                    }
                }
            }
        }
    }

    private static class Node {
        ArrayList<Pair<Integer, Integer>> adj;
        public Node() {
            adj = new ArrayList<>();
        }
    }
}
