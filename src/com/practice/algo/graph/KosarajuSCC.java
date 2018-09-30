package com.practice.algo.graph;

import java.util.Stack;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
// STRONGLY CONNECTED COMPONENTS
public class KosarajuSCC {


    public static void main(String[] args) {

        final int nodes = 5;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);

        boolean[] visited = new boolean[nodes + 1];

        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                dfs(visited, graph, i, stack);
            }
        }

        final GraphCreator transposeGraph = graph.transpose();

        for (int i = 1; i <= nodes; i++) {
            visited[i] = false;
        }

        int count = 0;

        System.out.println("\n\n");

        while (!stack.isEmpty()) {

            final int topNode = stack.pop();
            if (!visited[topNode]) {
                System.out.println("SCC #" + ++count + " -------------- ");
                dfs(visited, transposeGraph, topNode, count);
            }

        }

    }

    private static void dfs(final boolean[] visited, final GraphCreator graph, final int start, final Stack<Integer> stack) {
        visited[start] = true;
        graph.getNeighbouringEdges(start)
                .forEach(edge -> {
                    if (!visited[edge.dest]) {
                        System.out.println("Marked node " + start + " as visited, " + " now going to node ---> " + edge.dest);
                        dfs(visited, graph, edge.dest, stack);
                    }
                });
        System.out.println("Pushing into the stack -----> " + start);
        stack.push(start);
    }

    private static void dfs(final boolean[] visited, final GraphCreator graph, final int start, final int number) {
        visited[start] = true;
        System.out.println("Part of SCC #" + number + " node is ---------> " + start);
        graph.getNeighbouringEdges(start)
                .forEach(edge -> {
                    if (!visited[edge.dest]) {
                        dfs(visited, graph, edge.dest, number);
                    }
                });
    }
}


