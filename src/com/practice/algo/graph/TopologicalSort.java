package com.practice.algo.graph;

import java.util.Stack;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Date : 28 Sept, 2018
 * Time : 15:37 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class TopologicalSort {

    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {

        /*
         * ORDER OF INDEPENDENT TASKS :
         *
         * Topological sorting means, that if a directed edge is there from U -> V, then
         * in the topological sorting order, U should come before V, i.e. U has to be completed
         * before V Or V is dependent on U.
         */
        final int nodes = 6;

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        G.addEdge(5, 0);
        G.addEdge(4, 0);
        G.addEdge(5, 2);
        G.addEdge(4, 1);

        G.addEdge(3, 1);
        G.addEdge(2, 3);

        boolean[] visited = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(visited, G, i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(" Popping out from stack in the order of independent tasks(node) till now ----> " + stack.pop());
        }
    }

    private static void dfs(final boolean[] visited, final GraphCreator graph, final int start) {
        visited[start] = true;
        graph.adj(start)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(start);
                    if (!visited[otherEnd]) {
                        dfs(visited, graph, otherEnd);
                    }
                });
        stack.add(start);
    }
}
