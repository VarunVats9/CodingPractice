package com.practice.algorithmthread.session8;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class DCycleFreeFlow {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            Arrays.fill(nodes[i].minWeight, -1);
            nodes[i].id = i;
        }

        for (int i = 1; i <= m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int w = in.nextInt();
            nodes[x].adj.add(nodes[y]);
            nodes[y].adj.add(nodes[x]);
            nodes[x].edges.add(w);
            nodes[y].edges.add(w);
        }

        nodes[0].depth = 0;
        dfs(nodes[0]);
        binaryLifting(nodes);

        int q = in.nextInt();
        for (int i = 1; i <= q; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            Node lca = findLca(nodes[a], nodes[b]);
            int dA = nodes[a].depth - lca.depth;
            int minWeightA = findMinUpX(nodes[a], dA);
            int dB = nodes[b].depth - lca.depth;
            int minWeightB = findMinUpX(nodes[b], dB);
            out.println(Math.min(minWeightA, minWeightB));
        }
    }

    private int findMinUpX(Node node, int x) {
        int i = 0;
        int weight = Integer.MAX_VALUE;
        while (x > 0) {
            if ((x & 1) > 0) {
                weight = Math.min(weight, node.minWeight[i]);
                node = node.lift[i];
            }
            i++;
            x = x >> 1;
        }
        return weight;
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

    private Node moveUpX(Node node, int x) {
        int i = 0;
        while (x > 0) {
            if ((x & 1) > 0) {
                node = node.lift[i];
            }
            i++;
            x = x >> 1;
        }
        return node;
    }

    private void binaryLifting(Node[] nodes) {
        for (int e = 1; e <= 20; e++) {
            for (Node n : nodes) {
                if (n.lift[e-1] != null) {
                    n.lift[e] = n.lift[e-1].lift[e-1];
                    n.minWeight[e] = Math.min(n.minWeight[e-1], n.lift[e-1].minWeight[e-1]);
                }
            }
        }
    }

    private void dfs(Node node) {
        for (int i = 0; i < node.adj.size(); i++) {
            if (node.adj.get(i).depth == -1) {
                node.adj.get(i).depth = node.depth + 1;
                node.adj.get(i).lift[0] = node;
                node.adj.get(i).minWeight[0] = node.edges.get(i);
                dfs(node.adj.get(i));
            }
        }
    }

    static class Node {
        ArrayList<Node> adj = new ArrayList();
        ArrayList<Integer> edges = new ArrayList();
        Node[] lift = new Node[20];
        int[] minWeight = new int[20];
        int depth = -1;
        int id;
    }
}
