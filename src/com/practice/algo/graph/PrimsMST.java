package com.practice.algo.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.practice.algo.graph.util.Edge;
import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 26/09/18.
 */
// MINIMUM SPANNING TREE
public class PrimsMST {

    // Cycle detection is not required in Prims, unlike Kruskal, because here every time we add unique new node in the MST.

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

        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(Comparator.comparing(a -> a.weight));

        List<Integer> nodesAddedToGraph = new ArrayList<>();
        nodesAddedToGraph.add(1);
        visited[1] = true;
        System.out.println("Added Edge " + 1 + " with weight " + 0);

        minHeap.addAll(graph.getNeighbouringEdges(1));

        while (nodesAddedToGraph.size() != nodes) {

            final Edge topEdge = minHeap.poll();

            if (visited[topEdge.dest]) {
                continue;
            }

            minHeap.addAll(graph.getNeighbouringEdges(topEdge.dest));
            visited[topEdge.dest] = true;
            nodesAddedToGraph.add(topEdge.dest);
            System.out.println("Added Edge " + topEdge.src + "  -----> " + topEdge.dest + " with weight " + topEdge.weight);
        }

    }
}
