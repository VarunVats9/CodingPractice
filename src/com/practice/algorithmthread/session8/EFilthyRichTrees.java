package com.practice.algorithmthread.session8;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;

public class EFilthyRichTrees {

    static int count;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node();

        for (int i = 1; i < n; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            nodes[x].adj.add(nodes[y]);
            nodes[y].adj.add(nodes[x]);
        }

        nodes[0].in = 0;
        nodes[0].visited = 1;
        count = 0;
        dfs(nodes[0]);

        SegmentTree seg = new SegmentTree(n);

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int op = in.nextInt();
            if (op == 1) {
                int t = in.nextInt() - 1;
                int v = in.nextInt();
                seg.set(nodes[t].in, Math.log(v));
            } else {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                double l = seg.sum(nodes[u].in, nodes[u].out);
                double r = seg.sum(nodes[v].in, nodes[v].out);

                double ans = Math.max(Math.log(1e-9), Math.min(l-r, Math.log(1e9)));
                out.printf("%.10f\n", Math.exp(ans));
            }
        }
    }

    private void dfs(Node node) {
        for (Node n : node.adj) {
            if (n.visited == -1) {
                n.in = ++count;
                n.visited = 1;
                dfs(n);
            }
        }
        node.out = count;
    }

    static class Node {
        int in, out;
        int visited = -1;
        ArrayList<Node> adj = new ArrayList<>();
    }

    static class SegmentTree {

        int size = 1;
        double[] t;

        SegmentTree(int n) {
            while (size < n) size *= 2;
            t = new double[2*size];
        }

        void set(int i, double v, int x, int tl, int tr) {
            if (tr == tl) {
                t[x] = v;
                return;
            }
            int m = (tl + tr) / 2;
            if (i > m) {
                set(i, v, 2*x + 2, m+1, tr);
            } else {
                set(i, v, 2*x + 1, tl, m);
            }
            t[x] = t[2*x + 1] + t[2*x + 2];
        }

        void set(int i, double v) {
            set(i, v, 0, 0, size-1);
        }

        double sum(int x, int tl, int tr, int l, int r) {
            if (tr < l || tl > r) {
                return 0.0d;
            }
            if (tl == tr) {
                return t[x];
            }
            if (tl >= l && tr <= r) {
                return t[x];
            }
            int m = (tl + tr) / 2;
            return sum(2*x + 1, tl, m, l, r) + sum(2*x + 2, m+1, tr, l, r);
        }

        double sum(int l, int r) {
            return sum(0, 0, size-1, l, r);
        }

    }

}
