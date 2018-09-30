package com.practice.algo.graph;

import java.util.Stack;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 28/09/18.
 */
// ORDER OF INDEPENDENT TASKS.
public class TopologicalSort {

    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {

        final int nodes = 6;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        graph.addEdge(6, 1);
        graph.addEdge(5, 1);
        graph.addEdge(6, 3);
        graph.addEdge(5, 2);

        graph.addEdge(4, 2);
        graph.addEdge(3, 4);

        boolean[] visited = new boolean[nodes + 1];

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                dfs(visited, graph, i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(" Popping out from stack ----> " + stack.pop());
        }
    }

    private static void dfs(final boolean[] visited, final GraphCreator graph, final int start) {
        visited[start] = true;
        graph.getNeighbouringNodes(start)
                .forEach(node -> {
                    if (!visited[node.node]) {
                        dfs(visited, graph, node.node);
                    }
                });
        stack.add(start);
    }
}
