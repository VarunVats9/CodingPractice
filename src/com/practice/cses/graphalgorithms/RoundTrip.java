package com.practice.cses.graphalgorithms;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RoundTrip {
    static boolean[] visited;
    static Node[] nodes;
    static int[] parent;
    static boolean cycle;
    static int start, end;
    static List<Integer> path;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        nodes = new Node[n];
        visited = new boolean[n];
        parent = new int[n];
        path = new ArrayList<>();
        cycle = false;

        for (int i = 0; i < n; i++) nodes[i] = new Node();

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            nodes[x].adj.add(y);
            nodes[y].adj.add(x);
        }

        start = end = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && start == -1 && end == -1) {
                parent[i] = -1;
                dfs(i);
            }
        }

        if (!cycle) {
            out.println("IMPOSSIBLE");
        } else {
            int x = start;
            while (x != end) {
                path.add(x+1);
                x = parent[x];
            }
            path.add(end+1);
            path.add(start+1);

            out.println(path.size());
            for (int i = 0; i < path.size(); i++) {
                out.print(path.get(i));
                out.print(" ");
            }
            out.println();
        }
    }

    private void dfs(int i) {
        visited[i] = true;
        for (int j = 0; j < nodes[i].adj.size(); j++) {
            int neighbour = nodes[i].adj.get(j);
            if (visited[neighbour] && neighbour != parent[i]) {
                cycle = true;
                end = i;
                start = neighbour;
            }
            if (!visited[neighbour]){
                parent[neighbour] = i;
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
