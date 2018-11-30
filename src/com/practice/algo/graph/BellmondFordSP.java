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

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        G.addEdge(0, 1, -1);
        G.addEdge(1, 2, 2);
        G.addEdge(2, 3, -3);
        G.addEdge(3, 4, -5);
        G.addEdge(0, 4, 4);

        G.addEdge(1, 4, 3);
        G.addEdge(1, 3, -2);

        // Negative edge cycle.
        G.addEdge(3, 1, 1);

        double[] dp = new double[nodes];

        for (int i = 0; i < nodes; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        final int source = 0;

        dp[source] = 0;

        for (int i = 0; i < nodes; i++) {
            for (int j = source; j < nodes; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                final int start = j;
                G.adj(start)
                        .forEach(edge -> {
                            final int otherEnd = edge.destination(start);
                            dp[otherEnd] = Math.min(dp[start] + edge.getWeight(), dp[otherEnd]);
                        });
            }
        }

        for (int j = source; j < nodes; j++) {
            if (dp[j] == Integer.MAX_VALUE) {
                continue;
            }
            final int start = j;
            G.adj(start)
                    .forEach(edge -> {
                        final int otherEnd = edge.destination(start);
                        if (dp[start] + edge.getWeight() < dp[otherEnd]) {
                            System.out.println("Negative edge cycle is present");
                            isNegativeCyclePresent = true;
                            return;
                        }
                    });
        }

        for (int i = 0; i < nodes && !isNegativeCyclePresent; i++) {
            System.out.println("Shortest distance from source : " + source + " to destination : " + i + " has distance  ----> " + dp[i]);
        }

    }
}
