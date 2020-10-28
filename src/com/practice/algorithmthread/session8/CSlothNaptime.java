package com.practice.algorithmthread.session8;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSlothNaptime {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }

        for (int i = 1; i < n; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            nodes[x].adj.add(nodes[y]);
            nodes[y].adj.add(nodes[x]);
        }

        nodes[0].depth = 0;
        dfs(nodes[0]);
        binaryLifting(nodes);

        int q = in.nextInt();
        for (int i = 1; i <= q; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            Node lca = findLca(nodes[a], nodes[b]);
            int distance = nodes[a].depth + nodes[b].depth - 2 * lca.depth;
            if (c >= distance) {
                out.println(nodes[b].id + 1);
                continue;
            }
            if (c < (nodes[a].depth - lca.depth)) {
                out.println(moveUpX(nodes[a], c).id + 1);
            } else {
                int d = distance - c;
                out.println(moveUpX(nodes[b], d).id + 1);
            }
        }
    }

    private Node findLca(Node nodeA, Node nodeB) {
        int dA = nodeA.depth;
        int dB = nodeB.depth;

        if (dA == 0) return nodeA;
        if (dB == 0) return nodeB;

        if (dA > dB) {
            int diff = nodeA.depth - nodeB.depth;
            nodeA = moveUpX(nodeA, diff);
        }

        if (dB > dA) {
            int diff = nodeB.depth - nodeA.depth;
            nodeB = moveUpX(nodeB, diff);
        }

        if (nodeA == nodeB) return nodeA;

        while (nodeA != nodeB) {
            if (nodeA.lift[0] == nodeB.lift[0]) return nodeA.lift[0];
            for (int i = 1; i < 20; i++) {
                if (nodeA.lift[i] == nodeB.lift[i]) {
                    nodeA = nodeA.lift[i - 1];
                    nodeB = nodeB.lift[i - 1];
                    break;
                }
            }
        }

        return nodeA;
    }

    private Node moveUpX(Node nodeA, int x) {
        int i = 0;
        while (x > 0) {
            if ((x & 1) > 0)
                nodeA = nodeA.lift[i];
            i++;
            x = x >> 1;
        }
        return nodeA;
    }

    private void binaryLifting(Node[] nodes) {
        for (int e = 1; e <= 20; e++) {
            for (Node n : nodes) {
                if (n.lift[e-1] != null) {
                    n.lift[e] = n.lift[e-1].lift[e-1];
                }
            }
        }
    }

    private void dfs(Node node) {
        for (Node n : node.adj) {
            if (n.depth == -1) {
                n.depth = node.depth + 1;
                n.lift[0] = node;
                dfs(n);
            }
        }
    }

    static class Node {
        ArrayList<Node> adj = new ArrayList();
        Node[] lift = new Node[20];
        int depth = -1;
        int id;
    }

}
