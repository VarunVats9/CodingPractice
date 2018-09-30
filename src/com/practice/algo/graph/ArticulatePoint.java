package com.practice.algo.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 29/09/18.
 */
public class ArticulatePoint {

    static int time = 0;

    public static void main(String[] args) {

        final int nodes = 8;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);

        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        graph.addEdge(6, 8);

        int[] discv = new int[nodes + 1];
        boolean[] visited = new boolean[nodes + 1];
        int parent[] = new int[nodes + 1];
        int low[] = new int[nodes + 1];
        Set<Integer> ap = new HashSet<>();

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                dfs(discv, visited, parent, ap, low, i, graph);
            }
        }

        System.out.println(ap);
    }

    private static void dfs(final int[] discv, final boolean[] visited, final int[] parent, final Set<Integer> ap,
                            final int[] low, final int start, final GraphCreator graph) {

        // Independent child count for each parent.
        final AtomicInteger child = new AtomicInteger(0);
        visited[start] = true;
        discv[start] = low[start] = ++time;

        graph.getNeighbouringNodes(start)
                .forEach(node -> {
                    final int adj = node.node;

                    // All the unvisited neighbours (parent would have already been visited)
                    if (!visited[adj]) {

                        // Increase the child count, and remember if a parent has two children and both are connected
                        // down their subtree. The other one would get skipped, as it will be visited, by the time its turn would come.
                        child.getAndIncrement();
                        // Mark the parent.
                        parent[adj] = start;

                        // Call the dfs on the neighbour.
                        dfs(discv, visited, parent, ap, low, adj, graph);

                        // Neighbour dfs has been done, lets see if we can reduce low for the node.
                        low[start] = Math.min(low[start], low[adj]);

                        // Found the neighbours low greater than my discovery time, which means nowhere
                        // in its subtree it is connected to me or my parent.
                        // Most Important -------> It should not be ROOT.
                        if (discv[start] <= low[adj] && parent[start] != 0) {
                            ap.add(start);
                        }
                    }

                    // Already visited node, which means found a back edge. Now decrease the low for all the higher parents.
                    if (parent[start] != adj && visited[adj]) {
                        low[start] = Math.min(low[start], low[adj]);
                    }
                });

        // Found two independent children, and the parent is 0 (NULL), i.e. it is root.
        if (child.get() > 1 && parent[start] == 0) {
            ap.add(start);
        }
    }

}
