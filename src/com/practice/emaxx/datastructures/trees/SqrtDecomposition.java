package com.practice.emaxx.datastructures.trees;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vvats on 13/11/18.
 */
public class SqrtDecomposition {

    // Total blocks.
    static int[] b;

    // Each block size.
    static int s;

    static int result = 0;

    static int[] cnt;

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

    /*
     * Mo's Algorithm.
     *
     * 1. It works in offline mode : that is all the queries need to be known beforehand.
     * 2. Array is not changed by queries.
     */
    private static void mo_algorithm(final List<Query> queries, final int[] a) {

        s = (int)(Math.sqrt(a.length) + 1);

        cnt = new int[1000_002];

        queries.sort((o1, o2) -> {
            Double c = o1.l * 1.0 / s;
            Double d = o2.l * 1.0 / s;
            if (c.equals(d)) {
                return o1.getR().compareTo(o2.getR());
            }
            return c.compareTo(d);
        });

        int c_l = 0;
        int c_r = -1;

        for (Query query : queries) {

            /*
             * Suppose, c_l is 3, and query.l is 2. And we already have, c_l calculated.
             * Now, have to decrease c_l (3 --> 2), and add that value to result. But, result for idx 3
             * we already have, hence --cl first, and then call add().
             */
            while (c_l > query.l) {
                add(--c_l, a);
            }

            /*
             * Suppose, c_l is 3, and query.l is 4. And we already have, c_l calculated.
             * Now, have to increase c_l (3 --> 4), and remove that value from result. But, result for idx 3
             * we already have, hence cl++ first, and then call remove().
             */
            while (c_l < query.l) {
                remove(c_l++, a);
            }

            /*
             * Suppose, c_r is 3, and query.r is 2. And we already have, c_r calculated.
             * Now, have to decrease c_r (3 --> 2), and remove that value from result. But, result for idx 3
             * we already have, hence cr-- first, and then call remove().
             */
            while (c_r > query.r) {
                remove(c_r--, a);
            }

            /*
             * Suppose, c_r is 3, and query.r is 4. And we already have, c_r calculated.
             * Now, have to increase c_r (3 --> 4), and add that value to result. But, result for idx 3
             * we already have, hence ++cr first, and then call add().
             */
            while (c_r < query.r) {
                add(++c_r, a);
            }

            System.out.println("Answer for query number : " + query.q_idx + " is : " + getAnswer());
        }

    }

    private static int getAnswer() {
        return result;
    }

    private static void remove(final int idx, final int[] a) {
        cnt[a[idx]]--;
        if (cnt[a[idx]] == 2) {
            result--;
        }
    }

    private static void add(final int idx, final int[] a) {
        cnt[a[idx]]++;
        if (cnt[a[idx]] == 3) {
            result++;
        }
    }

    public static class Query {
        int l, r, q_idx;

        public Query(final int l, final int r, final int q_idx) {
            this.l = l;
            this.r = r;
            this.q_idx = q_idx;
        }

        public Integer getR() {
            return this.r;
        }
    }

    public static void main(String[] args) {

        {
            int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
            SqrtDecomposition.decomposition(a);
            SqrtDecomposition.sumQuery(2, 6, a);
        }

        {
            // Distinct elements between l and r, which are repeated at least three times.
            int[] a = new int[]{0, 1, 1, 0, 2, 3, 4, 1, 3, 5, 1, 5, 3, 5, 4, 0, 2, 2};
            final List<Query> queries = Arrays.asList(new Query(0, 8, 1), new Query(2, 5, 2), new Query(2, 10, 3),
                    new Query(16, 17, 4), new Query(13, 14, 5), new Query(1, 17, 6), new Query(17, 17, 7));
            SqrtDecomposition.mo_algorithm(queries, a);
        }

    }

}
