package com.practice.youtube.rachitjain;

import java.util.ArrayList;
import java.util.List;

/**
 * Date : 24 Nov, 2018
 * Time : 6:18 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class DpTreesC_LCA {


    private static void lca(final List<Integer>[] g, final int a, final int b, final int nodes) {

        int[][] dp = new int[nodes+1][];


    }



    public static void main(String[] args) {

        {

            int nodes = 14;
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

            DpTreesC_LCA.lca(g, 3, 7, nodes);
        }

    }


}

