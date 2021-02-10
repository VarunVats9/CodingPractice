package com.practice.cses.graphalgorithms;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class MessageRoute {

    static boolean[] visited;
    static Node[] nodes;
    static Queue<Integer> queue;
    static int[] parent;
    static List<Integer> path;
    static int n, m;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        nodes = new Node[n];
        visited = new boolean[n];
        parent = new int[n];
        path = new ArrayList<>();
        queue = new LinkedList<>();

        for (int i = 0; i < n; i++) nodes[i] = new Node();

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            nodes[x].adj.add(y);
            nodes[y].adj.add(x);
        }

        dfs(0);

        if (!visited[n-1]) {
            out.println("IMPOSSIBLE");
            return;
        }

        visited = new boolean[n];
        bfs();
        backtrack();
        out.println(path.size());
        for (int i = 0; i < path.size(); i++) {
            out.print(path.get(i) + " ");
        }
        out.println();
    }

    private void backtrack() {
        int x = n - 1;
        while (x != -1) {
            path.add(x+1);
            x = parent[x];
        }
        Collections.reverse(path);
    }

    private void bfs() {
        parent[0] = -1;
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            for (int j = 0; j < nodes[top].adj.size(); j++) {
                int neighbour = nodes[top].adj.get(j);
                if (!visited[neighbour]) {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                    parent[neighbour] = top;
                }
            }
        }
    }

    private void dfs(int i) {
        visited[i] = true;
        for (int j = 0; j < nodes[i].adj.size(); j++) {
            int neighbour = nodes[i].adj.get(j);
            if (!visited[neighbour]){
                dfs(neighbour);
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
