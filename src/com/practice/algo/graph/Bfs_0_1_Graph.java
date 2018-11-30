package com.practice.algo.graph;

import java.util.ArrayDeque;
import java.util.Deque;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 30/10/18.
 */
public class Bfs_0_1_Graph {


    public static void main(String[] args) {

        final int nodes = 9;

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        G.addEdge(0, 1, 0);
        G.addEdge(0, 7, 1);
        G.addEdge(1, 7, 1);
        G.addEdge(1, 2, 1);
        G.addEdge(2, 3, 0);
        G.addEdge(2, 5, 0);
        G.addEdge(2, 8, 1);
        G.addEdge(3, 4, 1);
        G.addEdge(3, 5, 1);
        G.addEdge(4, 5, 1);
        G.addEdge(5, 6, 1);
        G.addEdge(6, 7, 1);
        G.addEdge(7, 8, 1);

        // Source is 0, distance from it.
        int[] distance = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[0] = 0;

        Deque<Integer> dequeue = new ArrayDeque<>();
        dequeue.addFirst(0);

        while (!dequeue.isEmpty()) {

            final int vertex = dequeue.removeFirst();

            G.adj(vertex)
                    .forEach(edge -> {
                        final int other = edge.destination(vertex);
                        if (distance[other] > distance[vertex] + edge.getWeight()) {
                            distance[other] = distance[vertex] + (int)edge.getWeight();
                            if (edge.getWeight() == 0) {
                                dequeue.addFirst(other);
                            } else {
                                dequeue.addLast(other);
                            }
                        }
                    });
        }

        printArray(distance);
    }

    private static void printArray(final int[] distance) {
        System.out.println("Distance from source to each node : ");
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + " ");
        }
    }

}
