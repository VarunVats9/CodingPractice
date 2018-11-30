package com.practice.algo.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.practice.algo.graph.util.Edge;
import com.practice.algo.graph.util.GraphCreator;

/**
 * Date : 26 Sept, 2018
 * Time : 10:37 AM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class PrimsMST {

    public static void main(String[] args) {

        /*
         * MINIMUM SPANNING TREE :
         *
         * Prims -> Node
         * Kruskal -> Edge
         *
         * In Prims, we subsequently add a unique node to a set, till it has all the nodes.
         * The edge with the least weight is added, each time.
         *
         * Cycle detection is not required in Prims, unlike Kruskal,
         * because here every time we add unique new node in the MST.
         */
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

        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();

        // Set of Nodes
        Set<Integer> nodeSet = new HashSet<>();

        // Mark the source as visited.
        visited[1] = true;
        System.out.println("Added Edge " + 1 + " with weight " + 0);

        // Add all the destination nodes / neighbours, of the node '1'.
        minHeap.addAll(G.adj(1));

        // Add this source to the set.
        nodeSet.add(1);

        while (nodeSet.size() != nodes) {

            final Edge topEdge = minHeap.poll();

            final int src = topEdge.source();
            final int dest = topEdge.destination(src);

            int newSource = dest;

            // Check if this new source / neighbour has already been visited.
            if (visited[newSource]) continue;

            // Mark the source as visited.
            visited[newSource] = true;

            // Add all the destination nodes / neighbours
            minHeap.addAll(G.adj(newSource));

            // Add this new source to the set.
            nodeSet.add(newSource);

            System.out.println("Added Edge " + src + "  -----> " + newSource + " with weight " + topEdge.getWeight());
        }

    }
}
