package com.practice.edu.segmenttrees.step1;

import com.practice.egork.lib.collections.Pair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class CNumberOfMinimumsOnASegment {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = in.readIntArray(n);
        SegmentTree sg = new SegmentTree(n);
        sg.build(arr);
        for (int i = 1; i <= q; i++) {
            int op = in.nextInt();
            if (op == 1) {
                int t = in.nextInt();
                int v = in.nextInt();
                sg.set(t, v);
            } else {
                int l = in.nextInt();
                int r = in.nextInt();
                Pair p = sg.min(l, r-1);
                out.println(p.first + " " + p.second);
            }
        }
    }

    static class SegmentTree {

        int size = 1;
        Pair<Integer, Integer>[] t;

        SegmentTree(int n) {
            while (size < n) size *= 2;
            t = new Pair[2*size];
            Arrays.fill(t, Pair.makePair(Integer.MAX_VALUE, 0));
        }

        void build(int[] arr, int x, int tl, int tr) {
            if (tl == tr) {
                if (tl < arr.length) t[x] = Pair.makePair(arr[tl], 1);
                return;
            }
            int m = (tl + tr) / 2;
            build(arr, 2*x + 1, tl, m);
            build(arr, 2*x + 2, m+1, tr);
            t[x] = min(t[2*x + 1], t[2*x + 2]);
        }

        Pair min(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
            if (a.first < b.first)
                return Pair.makePair(a.first, a.second);
            if (a.first > b.first)
                return Pair.makePair(b.first, b.second);
            return Pair.makePair(b.first, a.second + b.second);
        }

        void build(int[] arr) {
            build(arr, 0, 0, size-1);
        }

        void set(int i, int v, int x, int tl, int tr) {
            if (tr == tl) {
                t[x] = Pair.makePair(v, 1);
                return;
            }
            int m = (tl + tr) / 2;
            if (i > m) {
                set(i, v, 2*x + 2, m+1, tr);
            } else {
                set(i, v, 2*x + 1, tl, m);
            }
            t[x] = min(t[2*x + 1], t[2*x + 2]);
        }

        void set(int i, int v) {
            set(i, v, 0, 0, size-1);
        }

        Pair min(int x, int tl, int tr, int l, int r) {
            if (tr < l || tl > r) {
                return Pair.makePair(Integer.MAX_VALUE, 0);
            }
            if (tl == tr) {
                return t[x];
            }
            if (tl >= l && tr <= r) {
                return t[x];
            }
            int m = (tl + tr) / 2;
            return min(min(2*x + 1, tl, m, l, r), min(2*x + 2, m+1, tr, l, r));
        }

        Pair min(int l, int r) {
            return min(0, 0, size-1, l, r);
        }

    }

}
