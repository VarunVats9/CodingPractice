package com.practice.emaxx.datastructures.trees;

/**
 * Date : 03 Dec, 2018
 * Time : 1:13 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class SegmentTreeBasic {

    /*
     * Why do we use 4*n, as the array of segment trees.
     *
     * So, if we add up all the vertices of the tree.
     * 1 + 2 + 4 + 8 + 16 .... 2^log(n), it summation by Geometric Progression would be :
     *
     *
     * Sum of GP = (a * (pow(r, n) - 1))/(r-1), here n = ceil(log(n)) + 1, and r = 2, and a = 1
     * => pow(2, ceil(log(n))+1) - 1 => Ignoring 1 here.
     * => pow(2, ceil(log(n))+1)
     *
     * Now, this is less than pow(2, ceil(log(n))+2)
     * pow(2, ceil(log(n))+1) < pow(2, ceil(log(n))+2)
     * pow(2, ceil(log(n))+1) < pow(2, ceil(log(n)))*pow(2, 2)
     *
     * Now, as you know, pow(2, log(n)) is n.
     * RHS becomes 4n, hence pow(2, ceil(log(n))+1) < 4n
     *
     *
     * Prove that pow(2, log(n)) = n.
     *
     * Let's say y = pow(2, log(n))
     * Take log on both sides
     * => log(y) = log(pow(2, log(n)))
     * => log(y) = log(n)
     *
     * Hence, y = n.
     */

    static int n;
    static int[] t;

    /*
     * Complexity of this build method : O(4*n) ~ O(n)
     *
     * Since, in the worst case there can be 4n vertices, and we would need to fill all those.
     */
    private static void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl+tr)/2;
            build(a, 2*v, tl, tm);
            build(a, 2*v+1, tm+1, tr);
            t[v] = t[2*v] + t[2*v+1];
        }
    }

    /*
     * Complexity of this sum method : O(4 * log(n)) ~ O(log(n))
     *
     * So, the intuition behind this is :
     * At every level, the worst case is that you didn't find the exact matching of the range, so you
     * go both on the left and right child. So basically with every vertex you can do two recursive call.
     *
     * Now, suppose, we are at level n, and at this level, we reach as it was the worst case, of level n-1.
     * That is, we have made two recursive call from n-1 level's left child, and same from n-1 level's
     * right child.
     *
     * So, here we are with 4 vertices on level n, under analysis to find the exact range.
     * Let's call these four vertices as, ll, lr, rl, rr.
     * where lr means l is the (n-1)th level's left child and r is its right child in nth level.
     *
     * Now, in the worst case, again we couldn't find the range and we go down to (n+1)th level.
     *
     * Case 1 : Only ll, rr are not getting the range match. Then lr and rl would end here on this
     * level only. And again both will make 2 recursive calls each. That would make, again 4 vertices on
     * next level, under analysis.
     *
     * Case 2 : ll, and rr is out of scope, maybe because query range doesn't overlap iin these two.
     * Hence, again only lr and rl will make recursive calls.
     *
     * Case 3 : If only lr and rr are having query range overlap, in that case also, ll (out of scope), and
     * rl (full range match) would end here on this level.
     *
     * Hence, on each level, at maximum 4 vertices can be under observation/analysis. O(4*log(n)) ~ O(log(n))
     *
     */
    private static int sum(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (tl == l && tr == r) {
            return t[v];
        }

        int tm = (tl+tr)/2;
        return sum(2*v, tl, tm, l, Math.min(r, tm)) +
                sum(2*v+1, tm+1, tr, Math.max(l, tm+1), r);
    }

    /*
     * Complexity of this update method : O(log(n)) [Height of the tree]
     *
     * That is, for update the path from root to specific leaf is updated.
     */
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
            t[v] = t[2*v] + t[2*v+1];
        }
    }

    public static void main(String[] args) {
        {
            n = 5;
            t = new int[4*n];
            int[] a = new int[]{1, 3, -2, 8, -7};
            build(a, 1, 0, n-1);

            int l = 2, r = 4;
            System.out.println("Sum from l : " + l + " from r : " + r + " is [" + sum(1, 0, n-1, 2, 4) + "]");

            update(1, 0, n-1, 2, 3);
            System.out.println("Sum from l : " + l + " from r : " + r + " is [" + sum(1, 0, n-1, 2, 4) + "]");
        }
    }


}
