package com.practice.emaxx.datastructures.trees;

/**
 * Date : 03 Dec, 2018
 * Time : 9:21 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class SegmentTreeAdvanced_2 {

    /*
     * Question : Find the gcd of all the numbers in the given range.
     */

    static int n;
    static int[] t;

    private static void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl+tr)/2;
            build(a, 2*v, tl, tm);
            build(a, 2*v+1, tm+1, tr);
            t[v] = calculateGcd(t[2*v], t[2*v+1]);
        }
    }

    private static int gcd(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return Integer.MIN_VALUE;
        }
        if (tl == l && tr == r) {
            return t[v];
        }
        int tm = (tl+tr)/2;
        return calculateGcd(gcd(2*v, tl, tm, l, Math.min(r, tm)),
                gcd(2*v+1, tm+1, tr, Math.max(l, tm+1), r));
    }

    private static int calculateGcd(int a, int b) {
        if (b == 0 || b == Integer.MIN_VALUE) {
            return a;
        }
        if (a == Integer.MIN_VALUE) {
            return b;
        }
        return calculateGcd(b, a%b);
    }

    private static void update(int v, int tl, int tr, int pos, int newValue) {
        if (tl == tr) {
            t[v] = newValue;
        } else {
            int tm = (tl+tr)/2;
            if (pos <= tm) {
                update(2*v, tl, tm, pos, newValue);
            } else {
                update(2*v+1, tm+1, tr, pos, newValue);
            }
            t[v] = calculateGcd(t[2*v], t[2*v+1]);
        }
    }

    public static void main(String[] args) {

        {
            n = 5;
            t = new int[4*n];
            int[] a = new int[]{1, 6, 3, 9, 4};
            build(a, 1, 0, n-1);

            int l = 1, r = 3;
            System.out.println("GCD from l : " + l + " from r : " + r + " is [" + gcd(1, 0, n-1, l, r) + "]");

            update(1, 0, n-1, 2, 4);
            System.out.println("GCD from l : " + l + " from r : " + r + " is [" + gcd(1, 0, n-1, l, r) + "]");
        }

    }
}
