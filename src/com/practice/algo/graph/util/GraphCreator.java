package com.practice.algo.graph.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vvats on 26/09/18.
 */
public class GraphCreator {

    private int nodes;
    private Graph type;
    private List<Set<Node>> graph;

    public enum Graph {
        DIRECTED,
        UNDIRECTED
    }

    public GraphCreator(final int nodes, final Graph typeOfGraph) {
        this.nodes = nodes;
        this.type = typeOfGraph;
        this.createGraph();
    }

    private void createGraph() {
        graph = new ArrayList<>(this.nodes + 1);
        for (int i = 0; i <= this.nodes; i++) {
            graph.add(new HashSet<>());
        }
    }

    public void addEdge(final int x, final int y) {
        addEdge(x, y, 1);
    }

    public void addEdge(final int x, final int y, final int weight) {
        graph.get(x).add(new Node(y, weight));
        if (Graph.UNDIRECTED.equals(this.type)) {
            graph.get(y).add(new Node(x, weight));
        }
    }

    public Set<Node> getNeighbouringNodes(final int x) {
        return graph.get(x);
    }

    public Set<Edge> getNeighbouringEdges(final int x) {
        return graph.get(x)
                .stream()
                .map(node -> new Edge(x, node.node, node.weight))
                .collect(Collectors.toSet());
    }

    public GraphCreator transpose() {
        final GraphCreator newGraph = new GraphCreator(this.nodes, Graph.DIRECTED);
        newGraph.createGraph();
        for (int i = 1; i <= nodes; i++) {
            final int start = i;
            this.getNeighbouringNodes(i)
                    .forEach(node -> {
                        newGraph.addEdge(node.node, start);
                    });
        }
        return newGraph;
    }
}
