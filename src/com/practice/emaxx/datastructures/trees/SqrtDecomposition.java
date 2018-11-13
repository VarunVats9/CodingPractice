package com.practice.emaxx.datastructures.trees;

/**
 * Created by vvats on 13/11/18.
 */
public class SqrtDecomposition {

    // Total blocks.
    static int[] b;
    static int s;

    private static void decomposition(final int[] a) {

        s = (int)Math.sqrt(a.length) + 1;

        b = new int[s];

        /*
         * Pre-processing sum step, dividing the elements of a into blocks of size sqrt(n).
         * with each block containing elements sqrt(n). Hence, sqrt(n) * sqrt(n) = n.
         *
         * Last block can have less values.
         *
         * Example : n = 8, s = sqrt(8)+1 = 3.
         *
         * b[0] = a[0] + a[1] + a[2] {b[0/3 == 1/3 == 2/3]};
         * b[1] = a[3] + a[4] + a[5] {b[3/3 == 4/3 == 5/3]};
         * b[2] = a[6] + a[7] {b[6/3 == 7/3]};
         *
         */
        for (int i = 0; i < a.length; i++) {
            b[i/s] += a[i];
        }
    }

    private static void sumQuery(final int l, final int r, final int[] a) {

        int sum = 0;

        /*
         * The sum can be divided into calculating the answers, between l and r range.
         * where, l can occupy some part of one block, and r can also occupy some part of another block.
         * And in between the full blocks, each can be answered in O(1).
         *
         * If l = 1, and r = n, then O(sqrt(n) * 1) is the answer for the query.
         *
         * b[0] = a[0] + a[1] + a[2] + .... + a[s-1].
         * b[1] = a[s] + a[s+1] + ......... + a[2s-1].
         *
         * b[k] = Summation over (k.s to min(n-1, (k+1).s - 1)) { a[i] }.
         *
         *
         * Summation over (l, r) { a[i] } = Summation over tail of l + + Summation of in-between blocks
         *                                      + Summation over tail of r
         * =>  Summation over (l, (k+1).s - 1) { a[i] } + Summation over ((k+1), (p-1)) { b[i] }
         *      + Summation over (p.s, r) { a[i] }.
         *
         * In case both belong to the same block (i.e k == p), the above formula doesn't work, have to solve trivially.
         */

        // c_l represents k from the above formula.
        int c_l = l / s;
        // c_r represents p from the above formula.
        int c_r = r / s;

        if (c_l == c_r) {
            for (int i = l; i <= r; i++) {
                sum += a[i];
            }
        } else {
            int tail_l = 0;
            int tail_r = 0;

            for (int i = l; i <= (c_l+1)*s-1; i++) {
                tail_l += a[i];
            }

            for (int j = c_l+1; j <= c_r-1; j++) {
                sum += b[j];
            }

            for (int k = c_r*s; k <= r; k++) {
                tail_r += a[k];
            }

            sum = sum + tail_l +tail_r;
        }


        System.out.println("Total sum between zero based index, l : " + l +  " and r : " + r + " is : " + sum);
    }



    public static void main(String[] args) {

        {
            int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
            SqrtDecomposition.decomposition(a);
            SqrtDecomposition.sumQuery(2, 6, a);
        }

    }

}
