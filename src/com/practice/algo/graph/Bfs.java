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

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            final int node = queue.removeFirst();
            G.adj(node)
                    .forEach(edge -> {
                        final int otherEnd = edge.other(node);
                        if (!visited[otherEnd]) {
                            System.out.println("Neighbours of " + node + " are added ---> " + otherEnd);
                            queue.add(otherEnd);
                            visited[otherEnd] = true;
                        }
                    });
        }

    }
}
