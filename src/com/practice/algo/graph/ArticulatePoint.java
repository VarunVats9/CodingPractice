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

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 0);
        G.addEdge(2, 3);

        G.addEdge(3, 4);
        G.addEdge(4, 5);
        G.addEdge(4, 6);
        G.addEdge(5, 6);

        G.addEdge(5, 7);

        int[] discv = new int[nodes];
        boolean[] visited = new boolean[nodes];
        int parent[] = new int[nodes];
        int low[] = new int[nodes];
        Set<Integer> ap = new HashSet<>();

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                dfs(discv, visited, parent, ap, low, i, G);
            }
        }

        System.out.println(ap);
    }

    private static void dfs(final int[] discv, final boolean[] visited, final int[] parent, final Set<Integer> ap,
                            final int[] low, final int start, final GraphCreator G) {

        // Independent child count for each parent.
        final AtomicInteger child = new AtomicInteger(0);
        visited[start] = true;
        discv[start] = low[start] = ++time;

        G.adj(start)
                .forEach(edge -> {
                    final int adj = edge.destination(start);

                    // All the unvisited neighbours (parent would have already been visited)
                    if (!visited[adj]) {

                        // Increase the child count, and remember if a parent has two children and both are connected
                        // down their subtree. The other one would get skipped, as it will be visited, by the time its turn would come.
                        child.getAndIncrement();
                        // Mark the parent.
                        parent[adj] = start;

                        // Call the dfs on the neighbour.
                        dfs(discv, visited, parent, ap, low, adj, G);

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
