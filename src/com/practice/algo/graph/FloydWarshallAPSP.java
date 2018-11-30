package com.practice.algo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 28/09/18.
 */
// ALL PAIR SHORTEST PATH
public class FloydWarshallAPSP {

    final static int INF = Integer.MAX_VALUE/2;

    public static void main(String[] args) {

        final int nodes = 4;

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        G.addEdge(0, 1, 5);
        G.addEdge(1, 2, 3);
        G.addEdge(2, 3, 1);
        G.addEdge(0, 3, 10);


        double[][] dp = new double[nodes][nodes];

        for (int i = 0; i < nodes; i++) {
            final int vertex = i;
            final Map<Integer, Double> neighbours = G.adj(vertex)
                    .stream()
                    .collect(Collectors.toMap(e -> e.destination(vertex), e -> e.getWeight()));
            for (int j = 0; j < nodes; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }
                if (!neighbours.containsKey(j)) {
                    dp[i][j] = INF;
                    continue;
                }
                dp[i][j] = neighbours.get(j);
            }
        }

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                for (int k = 0; k < nodes; k++) {
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }

        tableCreation(nodes, dp);

    }

    private static void tableCreation(final int nodes, final double[][] dp) {
        final StringBuilder stringBuilder = new StringBuilder();
        int k = nodes;
        while (k > 0) {
            stringBuilder.append("%15s");
            k--;
        }
        for (int i = 0; i < nodes; i++) {
            System.out.print(i + " | ");
            final List<String> row = new ArrayList<>();
            for (int j = 0; j < nodes; j++) {
                final String value = dp[i][j] == INF ? "INF (" + j + ")" : String.valueOf(dp[i][j]) + " (" + j + ")";
                row.add(value);
            }
            System.out.format(stringBuilder.toString() + "\n", row.toArray());
        }
    }

}
