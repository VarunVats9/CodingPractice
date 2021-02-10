package com.practice.cses.graphalgorithms;

import com.practice.cses.graphalgorithms.BuildingRoads;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BuildingTeams {
    static boolean[] visited;
    static Node[] nodes;
    static char NO_COLOR = '0';
    static char[] color = new char[] {NO_COLOR, '2', '1'};
    static boolean contradiction = false;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        nodes = new Node[n];
        visited = new boolean[n];

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
                nodes[i].color = '1';
                dfs(i);
            }
        }

        if (contradiction) {
            out.println("IMPOSSIBLE");
        } else {
            for (int i = 0; i < n; i++) {
                out.print(nodes[i].color - NO_COLOR);
                out.print(" ");
            }
            out.println();
        }
    }

    private void dfs(int i) {
        visited[i] = true;
        for (int j = 0; j < nodes[i].adj.size(); j++) {
            int neighbour = nodes[i].adj.get(j);
            if (nodes[neighbour].color == nodes[i].color) {
                contradiction = true;
                return;
            } else {
                if (nodes[neighbour].color == NO_COLOR) {
                    nodes[neighbour].color = color[nodes[i].color - NO_COLOR];
                }
                if (!visited[neighbour]) {
                    dfs(neighbour);
                }
            }
        }
    }

    private static class Node {
        ArrayList<Integer> adj;
        Character color = NO_COLOR;
        public Node() {
            adj = new ArrayList<>();
        }
    }
}
