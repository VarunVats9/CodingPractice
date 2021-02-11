package com.practice.cses.graphalgorithms;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GameRoutes {

    static int n, m;
    static int[] inDegree;
    static int[] counts;
    static Node[] nodes;
    static boolean notPossible;
    static List<Integer> order;
    static PriorityQueue<Pair<Integer, Integer>> minHeap;
    static int MOD = 1_000_000_007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        nodes = new Node[n];
        counts = new int[n];
        inDegree = new int[n];
        order = new ArrayList<>();
        minHeap = new PriorityQueue<>((a, b) -> {
            if (a.second < b.second) return -1;
            else if (a.second > b.second) return 1;
            return a.first.compareTo(b.first);
        });

        for (int i = 0; i < n; i++) nodes[i] = new Node();

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            inDegree[y]++;
            nodes[x].adj.add(y);
        }

        topologicalSort();

        if (notPossible) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(counts[n-1]);
        }
    }

    private void topologicalSort() {
        for (int i = 0; i < n; i++) {
            minHeap.add(Pair.makePair(i, inDegree[i]));
        }

        if (minHeap.isEmpty()) return;
        counts[minHeap.peek().first] = 1;

        while (!minHeap.isEmpty()) {
            Pair<Integer, Integer> top = minHeap.poll();
            if (top.second > 0) {
                if (order.size() != n) {
                    notPossible = true;
                }
                return;
            }
            order.add(top.first);
            for (int i = 0; i < nodes[top.first].adj.size(); i++) {
                int neigh = nodes[top.first].adj.get(i);
                inDegree[neigh]--;
                counts[neigh] = mod(counts[neigh], counts[top.first]);
                minHeap.add(Pair.makePair(neigh, inDegree[neigh]));
            }
        }
    }

    private int mod(int a, int b) {
        return (a % MOD + b % MOD) % MOD;
    }

    private static class Node {
        ArrayList<Integer> adj;
        public Node() {
            adj = new ArrayList<>();
        }
    }
}
