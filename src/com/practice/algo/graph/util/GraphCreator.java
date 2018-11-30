package com.practice.algo.graph.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vvats on 26/09/18.
 */
public class GraphCreator {

    private int V;
    private int E;
    private Graph type;
    private List<Edge>[] adj;

    public enum Graph {
        DIRECTED,
        UNDIRECTED
    }

    public GraphCreator(final int V, final Graph typeOfGraph) {
        this.V = V;
        this.type = typeOfGraph;
        this.createGraph();
    }

    private void createGraph() {
        adj = new List[this.V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(final int v, final int w) {
        addEdge(v, w, 1);
    }

    public void addEdge(final int v, final int w, final int weight) {
        adj[v].add(new Edge(v, w, weight));
        if (Graph.UNDIRECTED.equals(this.type)) {
            adj[w].add(new Edge(w, v, weight));

        }
        this.E++;
    }

    public List<Edge> adj(final int v) {
        return adj[v];
    }

    public List<Edge> edges() {
        final List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < this.V; i++) {
            edges.addAll(adj(i));
        }
        return edges;
    }

    public GraphCreator reverse() {
        final GraphCreator newGraph = new GraphCreator(this.V, Graph.DIRECTED);
        newGraph.createGraph();
        for (int i = 0; i < V; i++) {
            final int start = i;
            this.adj(start)
                    .forEach(edge -> {
                        newGraph.addEdge(edge.destination(start), start);
                    });
        }
        return newGraph;
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }
}
