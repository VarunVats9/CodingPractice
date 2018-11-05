package com.practice.algo.graph;

import java.util.ArrayList;
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

        List<Integer> nodesAddedToGraph = new ArrayList<>();
        nodesAddedToGraph.add(1);
        visited[1] = true;
        System.out.println("Added Edge " + 1 + " with weight " + 0);

        minHeap.addAll(G.adj(1));

        while (nodesAddedToGraph.size() != nodes) {

            final Edge topEdge = minHeap.poll();

            final int currentVertex = topEdge.either();
            final int otherVertex = topEdge.other(currentVertex);
            int vertexDest = currentVertex;
            int vertexSrc = otherVertex;

            if (visited[currentVertex] && visited[otherVertex]) {
                continue;
            }

            if (visited[currentVertex]) {
                vertexDest = otherVertex;
                vertexSrc = currentVertex;
            }

            minHeap.addAll(G.adj(vertexDest));
            visited[vertexDest] = true;
            nodesAddedToGraph.add(vertexDest);
            System.out.println("Added Edge " + vertexSrc + "  -----> " + vertexDest + " with weight " + topEdge.getWeight());
        }

    }
}
