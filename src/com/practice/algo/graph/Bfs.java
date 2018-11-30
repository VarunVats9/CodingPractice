package com.practice.algo.graph;

import java.util.LinkedList;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
public class Bfs {

    public static void main(String[] args) {

        final int nodes = 9;

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        G.addEdge(0, 1, 4);
        G.addEdge(1, 2, 8);
        G.addEdge(2, 3, 7);
        G.addEdge(3, 4, 9);
        G.addEdge(4, 5, 10);
        G.addEdge(5, 6, 2);
        G.addEdge(6, 7, 1);
        G.addEdge(7, 0, 4);

        G.addEdge(1, 7, 11);
        G.addEdge(7, 8, 7);
        G.addEdge(8, 6, 6);

        G.addEdge(2, 8, 2);
        G.addEdge(2, 5, 4);
        G.addEdge(3, 5, 14);

        boolean visited[] = new boolean[nodes];
        int distance[] = new int[nodes];
        int parent[] = new int[nodes];

        LinkedList<Integer> queue = new LinkedList<>();
        // Source is 0;
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            final int node = queue.removeFirst();
            G.adj(node)
                    .forEach(edge -> {
                        final int otherEnd = edge.destination(node);
                        if (!visited[otherEnd]) {
                            System.out.println("Neighbours of " + node + " are added ---> " + otherEnd);
                            queue.add(otherEnd);
                            distance[otherEnd] = distance[node] + 1;
                            parent[otherEnd] = node;
                            visited[otherEnd] = true;
                        }
                    });
        }

        for (int i = 0; i < nodes; i++) {
            System.out.println("Distance from source (0) to " + i + " is : " + distance[i]);

            // Path from i to source.
            System.out.print("Path from : " + i + "-->");
            int j = i;
            while (parent[j] != 0) {
                System.out.print(parent[j] + "-->");
                j = parent[j];
            }
            System.out.println("0");
        }

    }
}
