package com.practice.cses.graphalgorithms;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class __TLE__LongestFlightRoute {

    static boolean[] visited;
    static Node[] nodes;
    static PriorityQueue<Pair<Integer, Long>> queue;
    static long[] dist;
    static int[] parent;
    static List<Integer> path;
    static int n, m;
    static boolean notPossible;
    static long INF = Long.MAX_VALUE;
    static int NINF = Integer.MIN_VALUE;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        nodes = new Node[n];
        parent = new int[n];
        visited = new boolean[n];
        dist = new long[n];
        path = new ArrayList<>();
        queue = new PriorityQueue<>(Comparator.comparing(a -> a.second));

        for (int i = 0; i < n; i++) nodes[i] = new Node();
        Arrays.fill(dist, INF);
        Arrays.fill(parent, NINF);

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            nodes[x].adj.add(Pair.makePair(y, -1));
        }

        sssp(0);
        makePath(n-1);
        if (notPossible) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(path.size());
            for (int i = 0; i < path.size(); i++) {
                out.print(path.get(i) + " ");
            }
        }
    }

    private void makePath(int x) {
        while (x != -1) {
            if (x == NINF) {
                notPossible = true;
                return;
            }
            path.add(x+1);
            x = parent[x];
        }
        Collections.reverse(path);
    }

    private void sssp(int s) {
        dist[s] = 0;
        parent[0] = -1;
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
                        parent[neigh] = pair.first;
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
