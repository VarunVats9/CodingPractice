package com.practice.algo.graph.util;

/**
 * Created by vvats on 26/09/18.
 */
public class Edge {
    public Integer src;
    public Integer dest;
    public int weight;

    public Edge(final Integer src, final Integer dest, final int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}
