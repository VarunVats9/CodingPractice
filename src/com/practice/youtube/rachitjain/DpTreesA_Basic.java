package com.practice.youtube.rachitjain;

import java.util.ArrayList;
import java.util.List;

/**
 * Date : 24 Nov, 2018
 * Time : 1:12 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class DpTreesA_Basic {

    /*
     * Reference Youtube : https://www.youtube.com/watch?v=gm4Ye0fESpU
     *
     * This question asks you to calculate the maximum sum from root to any leaf.
     * We have used dp, in this approach.
     */
    private static void treeSum(final List<Integer>[] g, final int[] weight, final int nodes) {

        int[] vis = new int[nodes+1];

        // dp[i], it means the maximum sum from root 'i' to any of the leaf in that sub tree of 'i'.
        int[] dp = new int[nodes+1];

        // Since it's a tree, no need of for loop, as just one dfs would traverse the whole.
        dfs(g, weight, dp, vis, 1);

        System.out.println("Maximum sum that is from root to any leaf is : " + dp[1]);
    }

    private static void dfs(final List<Integer>[] g, final int[] weight, final int[] dp, final int[] vis, final int curr) {

        vis[curr] = 1;
        int max = 0;

        // Add the root value to the sum.
        dp[curr] = weight[curr];

        for (int i = 0; i < g[curr].size(); i++) {
            int child = g[curr].get(i);
            if (vis[child] == 0) {
                dfs(g, weight, dp, vis, child);

                // Calculate the maximum subtree sum out of all the children.
                max = Math.max(max, dp[child]);
            }
        }

        // Add the maximum sum, to the root.
        dp[curr] = dp[curr] + max;
    }

    public static void main(String[] args) {

        {
            int nodes = 14;
            int[] weight = new int[]{0, 3, 2, 1, 10, 1, 3, 9, 1, 5, 3, 4, 5, 9, 8};
            List<Integer>[] g = new List[15];

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
            g[4].add(9); g[9].add(4);
            g[4].add(10); g[10].add(4);
            g[5].add(11); g[11].add(5);
            g[5].add(12); g[12].add(5);
            g[7].add(13); g[13].add(7);
            g[7].add(14); g[14].add(7);

            DpTreesA_Basic.treeSum(g, weight, nodes);
        }
    }


}
