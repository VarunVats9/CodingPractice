package com.practice.emaxx.datastructures.trees;

import com.practice.emaxx.geometry.Pair;

/**
 * Date : 03 Dec, 2018
 * Time : 5:21 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class SegmentTreeAdvanced_1 {

    /*
     * Question : Find the maximum, and the number of times it appears in the given range.
     */

    static int n;
    static Pair<Integer, Integer>[] t;

    private static void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = makePair(a[tl], 1);
        } else {
            int tm = (tl+tr)/2;
            build(a, 2*v, tl, tm);
            build(a, 2*v+1, tm+1, tr);
            t[v] = combine(t[2*v], t[2*v+1]);
        }
    }

    private static Pair<Integer, Integer> max(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return makePair(Integer.MIN_VALUE, 0);
        }
        if (tl == l && tr == r) {
            return t[v];
        }
        int tm = (tl+tr)/2;
        return combine(max(2*v, tl, tm, l, Math.min(r, tm)),
                max(2*v+1, tm+1, tr, Math.max(l, tm+1), r));
    }

    private static void update(int v, int tl, int tr, int pos, int newValue) {
        if (tl == tr) {
            t[v] = makePair(newValue, 1);
        } else {
            int tm = (tl+tr)/2;
            if (pos <= tm) {
                update(2*v, tl, tm, pos, newValue);
            } else {
                update(2*v+1, tm+1, tr, pos, newValue);
            }
            t[v] = combine(t[2*v], t[2*v+1]);
        }
    }

    private static Pair<Integer, Integer> makePair(final int left, final int right) {
        return new Pair<>(left, right);
    }

    private static Pair<Integer,Integer> combine(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        if (a.getLeft() > b.getLeft()) {
            return a;
        }
        if (b.getLeft() > a.getLeft()) {
            return b;
        }
        return makePair(a.getLeft(), a.getRight()+b.getRight());
    }

    public static void main(String[] args) {

        {
            n = 5;
            t = new Pair[4*n];
            int[] a = new int[]{1, 3, -2, 1, -7};
            build(a, 1, 0, n-1);

            int l = 2, r = 4;
            System.out.println("Maximum from l : " + l + " from r : " + r + " is [" + max(1, 0, n-1, l, r) + "]");

            update(1, 0, n-1, 2, 1);
            System.out.println("Maximum from l : " + l + " from r : " + r + " is [" + max(1, 0, n-1, l, r) + "]");
        }

    }
}
