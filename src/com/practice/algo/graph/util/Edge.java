package com.practice.algo.graph.util;

/**
 * Created by vvats on 26/09/18.
 */
public class Edge implements Comparable<Edge> {

    private final int v, w;
    private final double weight;

    public Edge(final int v, final int w, final double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int source() {
        return this.v;
    }

    public int destination(final int vertex) {
        if (vertex == v) return w;
        return v;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(final Edge that) {
        if (this.weight > that.weight) return 1;
        else if (this.weight < that.weight) return -1;
        return 0;
    }
}
