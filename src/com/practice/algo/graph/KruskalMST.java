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
        int parent[] = new int[nodes + 1];

        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>(Comparator.comparing(a -> a.weight));

        for (int i = 1; i <= nodes; i++) {
            minHeap.addAll(graph.getNeighbouringEdges(i));
        }

        Set<Integer> nodesAddedToGraph = new HashSet<>();

        while (nodesAddedToGraph.size() != nodes) {

            final Edge topEdge = minHeap.poll();

            final Integer srcNode = topEdge.src;
            final Integer destNode = topEdge.dest;

            if (visited[srcNode] && visited[destNode]) {
                if (findParent(parent, srcNode) == findParent(parent, destNode)) {
                    continue;
                }
                union(parent, srcNode, destNode);
                System.out.println("Added Edge " + srcNode + "  -----> " + destNode + " with weight " + topEdge.weight);
                continue;
            }

            if (!visited[srcNode]) {
                nodesAddedToGraph.add(srcNode);
                visited[srcNode] = true;
                if (!visited[destNode]) {
                    nodesAddedToGraph.add(destNode);
                    visited[destNode] = true;
                }
                union(parent, srcNode, destNode);
                System.out.println("Added Edge " + srcNode + "  -----> " + destNode + " with weight " + topEdge.weight);
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
