package com.practice.algo.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.practice.algo.graph.util.Edge;
import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
// SINGLE SOURCE SHORTEST PATH (POSITIVE WEIGHT)
public class DijkstraSP {

    final static Stack<Integer> stack = new Stack<>();

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
        graph.addEdge(8, 1, 8);

        graph.addEdge(2, 8, 11);
        graph.addEdge(8, 9, 7);
        graph.addEdge(9, 7, 6);

        graph.addEdge(3, 9, 2);
        graph.addEdge(3, 6, 4);
        graph.addEdge(4, 6, 14);

        boolean visited[] = new boolean[nodes + 1];
        int parent[] = new int[nodes + 1];

        final int source = 1;

        List<Integer> nodesAddedTillNow = new ArrayList<>();

        Queue<Edge> queue = new PriorityQueue<Edge>(Comparator.comparing(a -> a.weight));
        queue.add(new Edge(1, 1, 0));

        while (nodesAddedTillNow.size() != nodes) {

            final Edge top = queue.poll();
            if (visited[top.dest]) {
                continue;
            }
            visited[top.dest] = true;
            nodesAddedTillNow.add(top.dest);
            parent[top.dest] = top.src;

            final int weightOfNode = top.weight;

            System.out.println("Adding node ---> " + top.dest + " with distance: " + "[" + weightOfNode + "]");

            graph.getNeighbouringEdges(top.dest)
                    .forEach(edge -> {
                        queue.add(new Edge(top.dest, edge.dest, edge.weight + weightOfNode));
                    });

        }

        // find the path.
        for (int i = 1; i <= nodes; i++) {
            stack.clear();
            findParent(parent, i, source);
            System.out.println(stack);
        }

    }

    private static void findParent(final int[] parent, final int dest, final int source) {
        stack.add(dest);
        if (dest == source)
            return;
        findParent(parent, parent[dest], source);
    }
}
