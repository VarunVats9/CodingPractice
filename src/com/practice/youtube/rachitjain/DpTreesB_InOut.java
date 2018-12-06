package com.practice.youtube.rachitjain;

import java.util.ArrayList;
import java.util.List;

/**
 * Date : 24 Nov, 2018
 * Time : 3:24 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class DpTreesB_InOut {

    /*
     * Reference Youtube : https://www.youtube.com/watch?v=Xng1Od_v6Ug
     *
     * In this question, we need to find the height of the tree, considering each node as root,
     * one by one.
     *
     * Recurrence rule :
     *
     * dp[u] = max(in[u], out[u])
     * in[u] => already calculated in dp tree A.
     * out[u] => 1 + max(out[p], 1 + max(sibling))
     */
    private static void nodeHeightTree(final List<Integer>[] g, final int nodes) {

        int[] visA = new int[nodes+1];
        int[] visB = new int[nodes+1];

        // in[i], it means the maximum height from root 'i' to any of the leaf in that sub tree of 'i'.
        int[] in = new int[nodes+1];

        /*
         * out[u], it means the maximum height from root 'i' to any of the leaf outside,
         * that is exclude the subtree of 'i'.
         */
        int[] out = new int[nodes+1];

        // Since it's a tree, no need of for loop, as just one dfsA would traverse the whole.
        dfsA(g, in,  visA, 1);

        /*
         * Note in trees, even visited is not required, just that curr's child != parent. Given that you have passed
         * parent also, like dfsB(curr, parent), in this case visited not required.
         */
        dfsB(g, in, out,  visB, 1);

        for (int i = 1; i <= nodes; i++) {
            System.out.println("Maximum height of the root " + i + " is : " + Math.max(in[i], out[i]));
        }
    }

    private static void dfsB(final List<Integer>[] g, final int[] in, final int[] out, final int[] vis, final int curr) {

        vis[curr] = 1;

        int mx1 = -1, mx2 = -1;

        // Find the top two values in adjacency list.
        for (int i = 0; i < g[curr].size(); i++) {
            int child = g[curr].get(i);
            if (vis[child] == 0) {
                if (in[child] >= mx1) {
                    mx2 = mx1;
                    mx1 = in[child];
                } else if (in[child] > mx2) {
                    mx2 = in[child];
                }
            }
        }

        for (int i = 0; i < g[curr].size(); i++) {
            int child = g[curr].get(i);
            if (vis[child] == 0) {
                int max = mx1;
                if (in[child] == max) {
                    max = mx2;
                }
                out[child] = 1 + Math.max(out[curr], 1 + max);
                dfsB(g, in, out, vis, child);
            }
        }

    }

    private static void dfsA(final List<Integer>[] g, final int[] in, final int[] vis, final int curr) {

        vis[curr] = 1;
        int max = 0;

        // Add the root height.
        in[curr] = 0;

        for (int i = 0; i < g[curr].size(); i++) {
            int child = g[curr].get(i);
            if (vis[child] == 0) {
                dfsA(g, in, vis, child);
                // Calculate the maximum subtree height out of all the children.
                max = Math.max(max, 1 + in[child]);
            }
        }

        // Add the maximum height, to the root.
        in[curr] = in[curr] + max;
    }



    public static void main(String[] args) {

        {
            int nodes = 10;
            List<Integer>[] g = new List[11];

            for (int i = 0; i <= nodes; i++) {
                g[i] = new ArrayList<>();
            }

            g[1].add(2); g[2].add(1);
            g[1].add(3); g[3].add(1);
            g[1].add(4); g[4].add(1);
            g[2].add(5); g[5].add(2);
            g[2].add(6); g[6].add(2);
            g[3].add(7); g[7].add(3);
            g[4].add(8); g[8].add(4);
            g[7].add(9); g[9].add(7);
            g[7].add(10); g[10].add(7);

            DpTreesB_InOut.nodeHeightTree(g, nodes);
        }

    }


}
