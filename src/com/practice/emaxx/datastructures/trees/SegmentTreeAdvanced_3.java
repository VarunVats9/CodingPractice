package com.practice.emaxx.datastructures.trees;

/**
 * Date : 04 Dec, 2018
 * Time : 8:53 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class SegmentTreeAdvanced_3 {

    /*
     * Question : Find the number of zeros in the given range, and the kth zero.
     */

    static int n;
    static int[] t;

    private static void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl] == 0 ? 1 : 0;
        } else {
            int tm = (tl+tr)/2;
            build(a, 2*v, tl, tm);
            build(a, 2*v+1, tm+1, tr);
            t[v] = t[2*v] + t[2*v+1];
        }
    }

    private static int countZero(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (tl == l && tr == r) {
            return t[v];
        }
        int tm = (tl+tr)/2;
        return countZero(2*v, tl, tm, l, Math.min(r, tm)) +
            countZero(2*v+1, tm+1, tr, Math.max(l, tm+1), r);
    }

    private static void update(int v, int tl, int tr, int pos, int newValue) {
        if (tl == tr) {
            t[v] = newValue == 0 ? 1 : 0;
        } else {
            int tm = (tl+tr)/2;
            if (pos <= tm) {
                update(2*v, tl, tm, pos, newValue);
            } else {
                update(2*v+1, tm+1, tr, pos, newValue);
            }
            t[v] = t[2*v] + t[2*v+1];
        }
    }

    public static void main(String[] args) {

        {
            n = 5;
            t = new int[4*n];
            int[] a = new int[]{1, 0, 3, 0, 0};
            build(a, 1, 0, n-1);

            int l = 1, r = 4;
            System.out.println("Zeros from l : " + l + " from r : " + r + " is [" + countZero(1, 0, n-1, l, r) + "]");

            int k = 3;
            System.out.println("The position of kth zero, where k : " + k + " is : " + kThZero(1, 0, n-1, k));

            update(1, 0, n-1, 2, 0);
            System.out.println("Zeros from l : " + l + " from r : " + r + " is [" + countZero(1, 0, n-1, l, r) + "]");
        }

    }

    private static int kThZero(int v, int tl, int tr, int k) {
        if (k > t[v]) {
            return -1;
        }
        if (tl == tr) {
            return tl;
        }
        int tm = (tl+tr)/2;
        if (k <= t[2*v]) {
            return kThZero(2*v, tl, tm, k);
        } else {
            return kThZero(2*v+1, tm+1, tr, k-t[2*v]);
        }
    }
}
