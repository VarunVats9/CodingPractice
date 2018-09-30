package com.practice.algo.graph;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
public class Dfs {

    public static void main(String[] args) {

        final int nodes = 9;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        graph.addEdge(1, 2, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 5, 9);
        graph.addEdge(5, 6, 10);
        graph.addEdge(6, 7, 2);
        graph.addEdge(7, 8, 1);
        graph.addEdge(8, 1, 4);

        graph.addEdge(2, 8, 11);
        graph.addEdge(8, 9, 7);
        graph.addEdge(9, 7, 6);

        graph.addEdge(3, 9, 2);
        graph.addEdge(3, 6, 4);
        graph.addEdge(4, 6, 14);

        boolean visited[] = new boolean[nodes + 1];

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                dfs(visited, graph, i);
            }
        }

    }

    private static void dfs(final boolean[] visited, final GraphCreator graph, final int start) {
        visited[start] = true;
        graph.getNeighbouringEdges(start)
                .forEach(edge -> {
                    if (!visited[edge.dest]) {
                        System.out.println("Marked node " + start + " as visited, " + " now going to node ---> " + edge.dest);
                        dfs(visited, graph, edge.dest);
                    }
                });
    }
}
