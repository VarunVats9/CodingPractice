package com.practice.algo.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.omg.CORBA.INTERNAL;

import com.practice.algo.graph.util.Edge;
import com.practice.algo.graph.util.GraphCreator;

/**
 * Date : 26 Sept, 2018
 * Time : 09:45 AM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class KruskalMST {

    public static void main(String[] args) {

        /*
         * Kruskal, considers the edges, and based on that add the nodes.
         *
         * Minimum Spanning Tree means that a tree, which is N nodes and N-1 edges, with least
         * edges cost, of the whole tree.
         *
         * Now, in this there are chances of edge cycles being included, so we have to take care of this.
         * Example : Suppose 1 -> 2, and 2 -> 3 have already been chosen, now what if someone choose, 1 -> 3
         * which is a CYCLE, and this whole representation is not a TREE anymore.
         *
         * Now, you must be thinking why would someone choose an edge which has both the ends already been
         * added as tree nodes ?
         *
         * Think of the cases like 1 -> 2 added, and then 3 -> 4 added. Now when you can choose, 2 -> 3 also,
         * as it unites two different sub trees. (Although 2 and 3, separately already been added to the tree.)
         *
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
        int parent[] = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            parent[i] = -1;
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();

        for (int i = 0; i < nodes; i++) {
            minHeap.addAll(G.adj(i));
        }

        Set<Integer> nodesAddedToGraph = new HashSet<>();

        while (nodesAddedToGraph.size() != nodes) {

            final Edge topEdge = minHeap.poll();

            final int src = topEdge.source();
            final int dest = topEdge.destination(src);

            if (visited[src] && visited[dest]) {
                if (findParent(parent, src) == findParent(parent, dest)) {
                    continue;
                }
                union(parent, src, dest);
                System.out.println("Added Edge " + src + "  -----> " + dest + " with weight " + topEdge.getWeight());
                continue;
            }

            if (!visited[src]) {
                nodesAddedToGraph.add(src);
                visited[src] = true;
                if (!visited[dest]) {
                    nodesAddedToGraph.add(dest);
                    visited[dest] = true;
                }
                union(parent, src, dest);
                System.out.println("Added Edge " + src + "  -----> " + dest + " with weight " + topEdge.getWeight());
            }
        }
    }

    private static void union(final int[] parent, final Integer srcNode, final Integer destNode) {
        final int srcNode_parent = findParent(parent, srcNode);
        final int destNode_parent = findParent(parent, destNode);

        /*
         * Find the parent of source, and destination.
         * Then either set parent of destination as parent of source or vice-versa.
         *
         * Because, however you set it, there will only be one parent to the whole tree.
         * And as you start merging trees, again there will only be one parent.
         */
        parent[srcNode_parent] = destNode_parent;
    }

    private static int findParent(final int[] parent, final Integer srcNode) {
        /*
         * Initially, all the nodes have parent as -1, or we can use the same
         * node as its parent.
         */
        if (parent[srcNode] == -1)
            return srcNode;
        return findParent(parent, parent[srcNode]);
    }

}
