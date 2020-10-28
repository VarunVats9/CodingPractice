package com.practice.algorithmthread.session8;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class BDynamicDiameter {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }

        for (int i = 1; i < n; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            nodes[x].adj.add(nodes[y]);
            nodes[y].adj.add(nodes[x]);
        }

        bfs(nodes[0], nodes);
        Node farthest = farthest(nodes);
        bfs(farthest, nodes);
        farthest = farthest(nodes);
        int origDiam = farthest.dist;
        for (Node m : nodes) m.diam |= origDiam == m.dist;
        bfs(farthest, nodes);
        for (Node m : nodes) m.diam |= origDiam == m.dist;

        for (int i = 0; i < n; i++) {
            if (nodes[i].diam)
                out.println(origDiam + 1);
            else
                out.println(origDiam);
        }
    }

    private Node farthest(Node[] nodes) {
        Node max = nodes[0];
        for (Node n : nodes) if (n.dist > max.dist) max = n;
        return max;
    }

    private void bfs(Node from, Node[] nodes) {
        for (Node n : nodes) n.dist = -1;
        from.dist = 0;
        ArrayDeque<Node> bfs = new ArrayDeque<>();
        bfs.add(from);
        while (!bfs.isEmpty()) {
            Node next = bfs.remove();
            for (Node n : next.adj) {
                if (n.dist == -1) {
                    n.dist = next.dist + 1;
                    bfs.add(n);
                }
            }
        }
    }

    static class Node {
        ArrayList<Node> adj = new ArrayList();
        int dist;
        boolean diam = false;
    }
}
