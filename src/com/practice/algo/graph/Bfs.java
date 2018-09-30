package com.practice.algo.graph;

import java.util.LinkedList;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
public class Bfs {

    public static void main(String[] args) {

        final int nodes = 9;

        final GraphCreator graph = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        graph.addEdge(1, 2, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 5, 9);
        graph.addEdge(5, 6, 10);
        graph.addEdge(6, 7, 2);
        graph.addEdge(7, 8, 1);
        graph.addEdge(8, 1, 4);

        graph.addEdge(2, 8, 11);
        graph.addEdge(8, 9, 7);
        graph.addEdge(9, 7, 6);

        graph.addEdge(3, 9, 2);
        graph.addEdge(3, 6, 4);
        graph.addEdge(4, 6, 14);

        boolean visited[] = new boolean[nodes + 1];

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            final int node = queue.removeFirst();
            graph.getNeighbouringEdges(node)
                    .forEach(edge -> {
                        if (!visited[edge.dest]) {
                            System.out.println("Neighbours of " + node + " are added ---> " + edge.dest);
                            queue.add(edge.dest);
                            visited[edge.dest] = true;
                        }
                    });
        }

    }
}
