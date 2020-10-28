package com.practice.algorithmthread.session8;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class ACircumferenceOfATree {

    static ArrayList[] adj = null;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        adj = new ArrayList[n+1];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList();
        }

        for (int i = 1; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        int last = bfs(queue, visited);

        visited.clear();
        Queue<Pair<Integer, Integer>> queueWithLevel = new LinkedList<>();
        queueWithLevel.add(Pair.makePair(last, 0));
        int count = bfsWithCount(queueWithLevel, visited);

        out.println(3 * count);
    }

    private int bfsWithCount(Queue<Pair<Integer, Integer>> queue, Set<Integer> visited) {
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.remove();
            int node = p.first;
            int level = p.second;
            for (Object neigh : adj[node]) {
                if (!visited.contains(neigh)) {
                    queue.add(Pair.makePair((Integer) neigh, level+1));
                }
            }
            visited.add(node);
            if (queue.isEmpty()) {
                return level;
            }
        }
        return -1;
    }

    private int bfs(Queue<Integer> queue, Set<Integer> visited) {
        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (Object neigh : adj[node]) {
                if (!visited.contains(neigh)) {
                    queue.add((Integer) neigh);
                }
            }
            visited.add(node);
            if (queue.isEmpty()) {
                return node;
            }
        }
        return -1;
    }

}
