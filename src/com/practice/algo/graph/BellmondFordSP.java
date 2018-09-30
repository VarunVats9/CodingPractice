package com.practice.algo.graph;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
// SINGLE SOURCE SHORTEST PATH (NEGATIVE WEIGHT)
public class BellmondFordSP {

    static boolean isNegativeCyclePresent = false;

    public static void main(String[] args) {

        final int nodes = 5;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        graph.addEdge(1, 2, -1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, -3);
        graph.addEdge(4, 5, -5);
        graph.addEdge(1, 5, 4);

        graph.addEdge(2, 5, 3);
        graph.addEdge(2, 4, -2);

        // Negative edge cycle.
        graph.addEdge(4, 2, 1);

        int[] dp = new int[nodes + 1];

        for (int i = 1; i <= nodes; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        final int source = 1;

        dp[source] = 0;

        for (int i = 1; i < nodes; i++) {
            for (int j = source; j <= nodes; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                final int start = j;
                graph.getNeighbouringNodes(start)
                        .forEach(node -> {
                            dp[node.node] = Math.min(dp[start] + node.weight, dp[node.node]);
                        });
            }
        }

        for (int j = source; j <= nodes; j++) {
            if (dp[j] == Integer.MAX_VALUE) {
                continue;
            }
            final int start = j;
            graph.getNeighbouringNodes(start)
                    .forEach(node -> {
                        if (dp[start] + node.weight < dp[node.node]) {
                            System.out.println("Negative edge cycle is present");
                            isNegativeCyclePresent = true;
                            return;
                        }
                    });
        }

        for (int i = 1; i <= nodes && !isNegativeCyclePresent; i++) {
            System.out.println("Shortest distance from source : " + source + " to destination : " + i + " has distance  ----> " + dp[i]);
        }

    }
}
