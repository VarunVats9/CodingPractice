package com.practice.algo.graph;

import java.util.ArrayList;
import java.util.List;

import com.practice.algo.graph.util.GraphCreator;

/**
 * Created by vvats on 27/09/18.
 */
public class Dfs {

    static int dfsTimer = 0;

    public static void main(String[] args) {

        final int nodes = 11;

        final GraphCreator G = new GraphCreator(nodes, GraphCreator.Graph.UNDIRECTED);

        G.addEdge(0, 1, 4);
        G.addEdge(1, 2, 8);
        G.addEdge(2, 3, 7);
        G.addEdge(3, 4, 9);
        G.addEdge(4, 5, 10);
        G.addEdge(5, 6, 2);
        G.addEdge(6, 7, 1);
        G.addEdge(7, 0, 4);

        G.addEdge(1, 7, 11);
        G.addEdge(7, 8, 7);
        G.addEdge(8, 6, 6);

        G.addEdge(2, 8, 2);
        G.addEdge(2, 5, 4);
        G.addEdge(3, 5, 14);

        G.addEdge(9, 10, 5);

        // Connected Components in undirected graph.
        List<Integer> cc = new ArrayList<>();

        // Color 0 : Unvisited, 1: Under visitation, 2: Exit visitation.
        int color[] = new int[nodes];

        // Edge classification.
        int[] entry = new int[nodes];
        int[] exit = new int[nodes];

        /*
         * Edge Classification:
         *
         * 1. Tree Edge : Edge from current node (u) to node (v). Color of v is 0, i.e v is discovered
         *                for the first time.
         * 2. Forward Edge : There is a edge(u, v) from current node(u) to descendant(v) of it.
         *                   entry[u] < entry[v]. And the color of v is 2.
         * 3. Back Edge : There is a edge(u, v) from current node(u) to ascendant(v) of it.
         *                entry[u] > entry[v]. And the color of v is 1. [It tells about cycles]
         * 4. Cross Edge : There is a edge(u, v), where the color of v is 2, and entry[u] > entry[v].
         *                 Formal definition, quite confusing, which is v is neither a descendant, or
         *                 ascendant of u.
         *
         *
         * In undirected graphs, we don't have back and cross edge, because in the dfs from u those
         * were already been discovered.
         */

        dfsTimer = 0;

        for (int i = 0; i < nodes; i++) {
            if (color[i] == 0) {
                // Clearing the previous connected components list.
                cc.clear();

                dfs(color, G, i, entry, exit, cc);

                // Print connected components list.
                System.out.print("Connected Components elements: ");
                for (int j = 0; j < cc.size(); j++) {
                    System.out.print(cc.get(j) + " ");
                }
                System.out.println("\n-------------------------------");
            }
        }
    }

    private static void dfs(final int[] color, final GraphCreator G, final int start, final int[] entry, final int[] exit, final List<Integer> cc) {

        color[start] = 1;
        entry[start] = dfsTimer++;

        // Adding into connected components.
        cc.add(start);

        G.adj(start)
                .forEach(edge -> {
                    final int otherEnd = edge.destination(start);
                    if (color[otherEnd] == 0) {
                        System.out.println("Marked node " + start + " as color 1: Under visitation," + " now going to node ---> " + otherEnd);
                        dfs(color, G, otherEnd, entry, exit, cc);
                    }
                });

        color[start] = 2;
        exit[start] = dfsTimer++;
    }
}
