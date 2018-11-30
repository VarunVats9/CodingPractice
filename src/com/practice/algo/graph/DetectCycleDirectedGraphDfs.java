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

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(0, 3);
        G.addEdge(3, 4);
        G.addEdge(4, 1);
        G.addEdge(4, 5);
        G.addEdge(5, 3);

        boolean[] visited = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(visited, G, i, new HashSet<>());
            }
        }

    }

    private static void dfs(final boolean[] visited, final GraphCreator G, final int start, final Set<Integer> parentSet) {
        visited[start] = true;
        // Add to the current stack.
        parentSet.add(start);
        G.adj(start)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(start);
                    if (parentSet.contains(otherEnd)) {
                        System.out.println("Found a back edge from " + start + " ----> " + otherEnd);
                        return;
                    }
                    if (!visited[otherEnd]) {
                        System.out.println("Marked node " + start + " as visited, " + " now going to node ---> " + otherEnd);
                        dfs(visited, G, otherEnd, parentSet);
                    }
                });
        // Remove from the current stack.
        parentSet.remove(start);
    }
}
