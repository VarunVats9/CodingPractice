package com.practice.cses.graphalgorithms;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class CourseSchedule {

    static int n, m;
    static int[] indegree;
    static Node[] nodes;
    static boolean notPossible;
    static List<Integer> order;
    static PriorityQueue<Pair<Integer, Integer>> minHeap;


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        nodes = new Node[n];
        indegree = new int[n];
        order = new ArrayList<>();
        minHeap = new PriorityQueue<>(Comparator.comparing(a -> a.second));

        for (int i = 0; i < n; i++) nodes[i] = new Node();

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            indegree[y]++;
            nodes[x].adj.add(y);
        }

        topologicalSort();

        if (notPossible) {
            out.println("IMPOSSIBLE");
        } else {
            for (int i = 0; i < order.size(); i++) {
                out.print(order.get(i) + " ");
            }
            out.println();
        }
    }

    private void topologicalSort() {
        for (int i = 0; i < n; i++) {
            minHeap.add(Pair.makePair(i, indegree[i]));
        }

        if (minHeap.peek().second > 0) {
            notPossible = true;
            return;
        }

        while (!minHeap.isEmpty()) {
            Pair<Integer, Integer> top = minHeap.poll();
            if (top.second > 0) {
                if (order.size() != n) {
                    notPossible = true;
                }
                return;
            }
            order.add(top.first + 1);
            for (int i = 0; i < nodes[top.first].adj.size(); i++) {
                int neigh = nodes[top.first].adj.get(i);
                indegree[neigh]--;
                minHeap.add(Pair.makePair(neigh, indegree[neigh]));
            }
        }
    }

    private static class Node {
        ArrayList<Integer> adj;
        public Node() {
            adj = new ArrayList<>();
        }
    }
}
