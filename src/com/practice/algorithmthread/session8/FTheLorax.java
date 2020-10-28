package com.practice.algorithmthread.session8;

import com.practice.algorithmthread.session8.EFilthyRichTrees;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FTheLorax {

    static int count = 0;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();

            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) nodes[i] = new Node();

            for (int i = 1; i < n; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                nodes[u].adj.add(nodes[v]);
                nodes[v].adj.add(nodes[u]);
            }

            nodes[0].in = 0;
            count = 0;
            dfs(nodes[0]);

            SegmentTree seg = new SegmentTree(n);

            for (int i = 1; i <= q; i++) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                int x = in.nextInt();
                if (x == 0) {
                    int md = nodes[a].in > nodes[b].in ? a : b;
                    long ans = seg.sum(nodes[md].in, nodes[md].out);
                    out.println(Math.abs(ans));
                } else {
                    seg.set(nodes[a].in, +x);
                    seg.set(nodes[b].in, -x);
                }
            }
        }
    }

    static class SegmentTree {

        int size = 1;
        long[] t;

        SegmentTree(int n) {
            while (size < n) size *= 2;
            t = new long[2*size];
        }

        void set(int i, int v, int x, int tl, int tr) {
            if (tr == tl) {
                t[x] += v;
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

        void set(int i, int v) {
            set(i, v, 0, 0, size-1);
        }

        long sum(int x, int tl, int tr, int l, int r) {
            if (tr < l || tl > r) {
                return 0;
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

        long sum(int l, int r) {
            return sum(0, 0, size-1, l, r);
        }

    }

    private void dfs(Node node) {
        for (Node n : node.adj) {
            if (n.in == -1) {
                n.in = ++count;
                dfs(n);
            }
        }
        node.out = count;
    }

    static class Node {
        int in = -1, out;
        ArrayList<Node> adj = new ArrayList<>();
    }
}
