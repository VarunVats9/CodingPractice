package com.practice.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 29/09/18.
 */
public class Bridges {

    static int time = 0;

    public static void main(String[] args) {

        final int nodes = 8;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);

        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.addEdge(6, 8);

        int[] discv = new int[nodes + 1];
        boolean[] visited = new boolean[nodes + 1];
        int parent[] = new int[nodes + 1];
        int low[] = new int[nodes + 1];
        List<List<Integer>> bridges = new ArrayList<>();

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                dfs(discv, visited, parent, bridges, low, i, graph);
            }
        }

        System.out.println(bridges);
    }

    private static void dfs(final int[] discv, final boolean[] visited, final int[] parent, final List<List<Integer>> bridges,
                            final int[] low, final int start, final GraphCreator graph) {

        visited[start] = true;
        discv[start] = low[start] = ++time;

        graph.getNeighbouringNodes(start)
                .forEach(node -> {
                    final int adj = node.node;

                    // All the unvisited neighbours (parent would have already been visited)
                    if (!visited[adj]) {

                        // Mark the parent.
                        parent[adj] = start;

                        // Call the dfs on the neighbour.
                        dfs(discv, visited, parent, bridges, low, adj, graph);

                        // Neighbour dfs has been done, lets see if we can reduce low for the node.
                        low[start] = Math.min(low[start], low[adj]);

                        // Found the neighbours low greater than my discovery time, which means nowhere
                        // in its subtree it is connected to me or my parent. So, if the start ---> adj edge breaks
                        // there is no way we can reach adj or its subtree.
                        if (discv[start] < low[adj]) {
                            bridges.add(Arrays.asList(start, adj));
                        }
                    }

                    // Already visited node, which means found a back edge. Now decrease the low for all the higher parents.
                    if (parent[start] != adj && visited[adj]) {
                        low[start] = Math.min(low[start], low[adj]);
                    }
                });
    }
}
