package com.practice.algo.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.practice.algo.graph.util.Edge;
import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 26/09/18.
 */
// MINIMUM SPANNING TREE (HAVE TO TAKE CARE OF CYCLE IN THIS UNLIKE PRIMS).
public class KruskalMST {

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
        int parent[] = new int[nodes];

        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();

        for (int i = 0; i < nodes; i++) {
            minHeap.addAll(G.adj(i));
        }

        Set<Integer> nodesAddedToGraph = new HashSet<>();

        while (nodesAddedToGraph.size() != nodes) {

            final Edge topEdge = minHeap.poll();

            final int currentVertex = topEdge.either();
            final int otherVertex = topEdge.other(currentVertex);

            if (visited[currentVertex] && visited[otherVertex]) {
                if (findParent(parent, currentVertex) == findParent(parent, otherVertex)) {
                    continue;
                }
                union(parent, currentVertex, otherVertex);
                System.out.println("Added Edge " + currentVertex + "  -----> " + otherVertex + " with weight " + topEdge.getWeight());
                continue;
            }

            if (!visited[currentVertex]) {
                nodesAddedToGraph.add(currentVertex);
                visited[currentVertex] = true;
                if (!visited[otherVertex]) {
                    nodesAddedToGraph.add(otherVertex);
                    visited[otherVertex] = true;
                }
                union(parent, currentVertex, otherVertex);
                System.out.println("Added Edge " + currentVertex + "  -----> " + otherVertex + " with weight " + topEdge.getWeight());
            }
        }
    }

    private static void union(final int[] parent, final Integer srcNode, final Integer destNode) {
        final int srcNode_parent = findParent(parent, srcNode);
        final int destNode_parent = findParent(parent, destNode);
        parent[srcNode_parent] = destNode_parent;
    }

    private static int findParent(final int[] parent, final Integer srcNode) {
        if (parent[srcNode] == 0)
            return srcNode;
        return findParent(parent, parent[srcNode]);
    }

}
