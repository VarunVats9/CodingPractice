package com.practice.cses.graphalgorithms;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BuildingRoads {

    static boolean[] visited;
    static ArrayList<Integer> rootNodes;
    static Node[] nodes;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        nodes = new Node[n];
        visited = new boolean[n];
        rootNodes = new ArrayList<>();

        for (int i = 0; i < n; i++) nodes[i] = new Node();

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            x--; y--;
            nodes[x].adj.add(y);
            nodes[y].adj.add(x);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                rootNodes.add(i+1);
            }
        }

        out.println(rootNodes.size() - 1);
        for (int i = 0; i < rootNodes.size() - 1; i++) {
            out.println(rootNodes.get(i) + " " + rootNodes.get(i+1));
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
