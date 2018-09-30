package com.practice.algo.graph;

import java.util.HashSet;
import java.util.Set;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
public class DetectCycleDirectedGraphDfs {

    public static void main(String[] args) {

        final int nodes = 6;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);

        boolean[] visited = new boolean[nodes + 1];

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                dfs(visited, graph, i, new HashSet<>());
            }
        }

    }

    private static void dfs(final boolean[] visited, final GraphCreator graph, final int start, final Set<Integer> parentSet) {
        visited[start] = true;
        // Add to the current stack.
        parentSet.add(start);
        graph.getNeighbouringEdges(start)
                .forEach(edge -> {
                    if (parentSet.contains(edge.dest)) {
                        System.out.println("Found a back edge from " + start + " ----> " + edge.dest);
                        return;
                    }
                    if (!visited[edge.dest]) {
                        System.out.println("Marked node " + start + " as visited, " + " now going to node ---> " + edge.dest);
                        dfs(visited, graph, edge.dest, parentSet);
                    }
                });
        // Remove from the current stack.
        parentSet.remove(start);
    }
}
