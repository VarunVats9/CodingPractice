package com.practice.companies.swiggy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import com.practice.algo.graph.util.GraphCreator;

public class TopologicalSortParallelScheduler {

    public static final Stack<Integer> topologicalOrder = new Stack<>();

    public static final Map<Integer, Set<Integer>> nodeToIncomingEdge = new HashMap<>();

    public static void main(String[] args) {

        final int nodes = 6;

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.DIRECTED);

        // 1) 1 -> 2 -> 3, this means 1 is dependent on 2 and 2 is dependent on 3. Hence, 3 should be the first one to run.
        // 2 ) At some places, it means 1 has to be done prior to 2 and 2 prior to 3. Which makes 1 as root.
        // The only difference is reverse of graph. Here I', taking second (2) approach.

        G.addEdge(6, 1);
        G.addEdge(5, 1);
        G.addEdge(6, 3);
        G.addEdge(5, 2);

        G.addEdge(4, 2);
        G.addEdge(3, 4);

        boolean[] visited = new boolean[nodes + 1];

        for (int i = 1; i <= nodes; i++) {
            nodeToIncomingEdge.put(i, new HashSet<>());
        }

        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                topologicalSort(G, i, visited);
            }
        }

        final Map<Integer, Set<Integer>> nodeAtSameLevel = new HashMap<>();
        Set<Integer> topoSet = new HashSet<>(topologicalOrder);

        while (!topologicalOrder.isEmpty()) {
            final Integer node = topologicalOrder.pop();
            System.out.println("TopoOrder : " + node);
            dfsOnGraph(nodeAtSameLevel, topoSet, node, G, 0);
        }

        System.out.println(nodeAtSameLevel);

        // In parallel run all those at the same level, sequentially.
        for (int i = 0; i < nodes; i++) {
            Set<Integer> level = nodeAtSameLevel.get(i);
            if (Objects.isNull(level)) {
                continue;
            }
            //runParallellyAllNodes(level);
        }

    }

    private static void dfsOnGraph(final Map<Integer, Set<Integer>> nodeAtSameLevel,
        final Set<Integer> topoSet, final Integer node, final GraphCreator G, int count) {

        if (!topoSet.contains(node)) {
            return;
        }

        final Set<Integer> set = nodeAtSameLevel.get(count);

        if (Objects.isNull(set)) {
            nodeAtSameLevel.put(count, new HashSet<>());
        }

        nodeAtSameLevel.get(count).add(node);

        G.adj(node)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(node);
                    dfsOnGraph(nodeAtSameLevel, topoSet, otherEnd, G, count+1);
                });

        topoSet.remove(node);
    }

    private static void topologicalSort(final GraphCreator G, final int start, final boolean[] visited) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        G.adj(start)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(start);
                    nodeToIncomingEdge.get(otherEnd).add(start);
                    if (!visited[otherEnd]) {
                        topologicalSort(G, otherEnd, visited);
                    }
                });

        topologicalOrder.add(start);
    }


}
