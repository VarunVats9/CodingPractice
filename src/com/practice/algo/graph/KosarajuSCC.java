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

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 0);
        G.addEdge(1, 3);
        G.addEdge(3, 4);

        boolean[] visited = new boolean[nodes];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(visited, G, i, stack);
            }
        }

        final GraphCreator transposeGraph = G.reverse();

        for (int i = 0; i < nodes; i++) {
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

    private static void dfs(final boolean[] visited, final GraphCreator G, final int start, final Stack<Integer> stack) {
        visited[start] = true;
        G.adj(start)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(start);
                    if (!visited[otherEnd]) {
                        System.out.println("Marked node " + start + " as visited, " + " now going to node ---> " + otherEnd);
                        dfs(visited, G, otherEnd, stack);
                    }
                });
        System.out.println("Pushing into the stack -----> " + start);
        stack.push(start);
    }

    private static void dfs(final boolean[] visited, final GraphCreator G, final int start, final int number) {
        visited[start] = true;
        System.out.println("Part of SCC #" + number + " node is ---------> " + start);
        G.adj(start)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(start);
                    if (!visited[otherEnd]) {
                        dfs(visited, G, otherEnd, number);
                    }
                });
    }
}


