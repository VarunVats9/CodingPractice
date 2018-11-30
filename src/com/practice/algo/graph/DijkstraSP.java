package com.practice.algo.graph;

import java.util.ArrayList;
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

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        G.addEdge(0, 1, 4);
        G.addEdge(1, 2, 8);
        G.addEdge(2, 3, 7);
        G.addEdge(3, 4, 9);
        G.addEdge(4, 5, 10);
        G.addEdge(5, 6, 2);
        G.addEdge(6, 7, 1);
        G.addEdge(7, 0, 8);

        G.addEdge(1, 7, 11);
        G.addEdge(7, 8, 7);
        G.addEdge(8, 6, 6);

        G.addEdge(2, 8, 2);
        G.addEdge(2, 5, 4);
        G.addEdge(3, 5, 14);

        boolean visited[] = new boolean[nodes];
        int parent[] = new int[nodes];

        final int source = 0;

        List<Integer> nodesAddedTillNow = new ArrayList<>();

        Queue<Edge> queue = new PriorityQueue<Edge>();
        queue.add(new Edge(0, 0, 0));

        while (nodesAddedTillNow.size() != nodes) {

            final Edge topEdge = queue.poll();

            final int currentVertex = topEdge.source();
            final int otherVertex = topEdge.destination(currentVertex);
            int vertexDest = currentVertex;
            int vertexSrc = otherVertex;

            if (visited[currentVertex] && visited[otherVertex]) {
                continue;
            }

            if (visited[currentVertex]) {
                vertexDest = otherVertex;
                vertexSrc = currentVertex;
            }


            visited[vertexDest] = true;
            nodesAddedTillNow.add(vertexDest);
            parent[vertexDest] = vertexSrc;

            final double weightOfNode = topEdge.getWeight();

            System.out.println("Adding node ---> " + vertexDest + " with distance: " + "[" + weightOfNode + "]");

            final int finalVertexDest = vertexDest;
            G.adj(vertexDest)
                    .forEach(edge -> {
                        final int otherEnd = edge.destination(finalVertexDest);
                        queue.add(new Edge(finalVertexDest, otherEnd, edge.getWeight() + weightOfNode));
                    });

        }

        // find the path.
        for (int i = 0; i < nodes; i++) {
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
