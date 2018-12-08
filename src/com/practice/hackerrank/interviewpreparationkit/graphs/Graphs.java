package com.practice.hackerrank.interviewpreparationkit.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Date : 03 Dec, 2018
 * Time : 11:29 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class Graphs {

    static long count = 0;
    /*
     * Question : Find which one is cheaper to build for each connected component,
     * a library in each city, or a library in one city and roads in that connected
     * components to be build, so that every city has access to other city.
     */
    private static long roadsAndLibraries(int n, int cLib, int cRoad, int[][] cities) {
        List<Integer>[] graph = new List[n+1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < cities.length; i++) {
            int a = cities[i][0];
            int b = cities[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[n+1];

        long cost = 0;

        for (int i = 1; i <= n; i++) {
            count = 0;
            if (!visited[i]) {
                dfs(i, visited, graph);
                cost += Math.min(cLib + (count - 1) * cRoad, count * cLib);
            }
        }

        return cost;
    }

    private static void dfs(final int node, final boolean[] visited, final List<Integer>[] graph) {
        visited[node] = true;
        count++;

        for (int i = 0; i < graph[node].size(); i++) {
            int neighbour = graph[node].get(i);
            if (!visited[neighbour]) {
                dfs(neighbour, visited, graph);
            }
        }
    }


    static int dist = 0;
    /*
     * Question : Find the shortest path between colors of same value 'val'. There can be many nodes of the
     * same value 'val'.
     */
    private static int findShortest(int n, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        List<Integer>[] graph = new List[n+1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < graphFrom.length; i++) {
            int a = graphFrom[i];
            int b = graphTo[i];
            graph[a].add(b);
            graph[b].add(a);
        }

        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == val) {
                dist = 0;
                boolean visited[] = new boolean[n+1];
                int[] parent = new int[n+1];
                bfs(ids, graph, i+1, visited, parent);
                minDist = Math.min(minDist, dist);
            }
        }

        if (minDist == Integer.MAX_VALUE) {
            return -1;
        }

        return minDist;
    }

    private static void bfs(final long[] ids, final List<Integer>[] graph, final int node,
            final boolean[] visited, final int[] parent) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(node);
        long color = ids[node-1];

        while (!queue.isEmpty()) {
            int fifo = queue.removeFirst();
            visited[fifo] = true;

            if (dist > 0 && ids[fifo-1] == color) {
                return;
            }

            for (int i = 0; i < graph[fifo].size(); i++) {
                int neighbour = graph[fifo].get(i);
                if (!visited[neighbour]) {
                    parent[neighbour] = parent[fifo] + 1;
                    dist = Math.max(dist, parent[neighbour]);
                    queue.addLast(neighbour);
                }
            }
        }

        dist = Integer.MAX_VALUE;
    }


    /*
     * Question : Find the shortest path between start node and all others. And if there is no
     * path between start and other, set the value as -1. Otherwise, 6 * distance.
     */
    public static class Graph {

        int nodes;
        List<Integer>[] graph;

        public Graph(int size) {
            nodes = size;
            graph = new List[nodes];
            for (int i = 0; i < nodes; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        public void addEdge(int first, int second) {
            graph[first].add(second);
            graph[second].add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int[] dist = new int[nodes];
            Arrays.fill(dist, -1);
            dist[startId] = 0;
            bfs(graph, startId, dist);
            return dist;
        }

        private void bfs(final List<Integer>[] graph, final int node, final int[] dist) {
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(node);

            while (!queue.isEmpty()) {
                int fifo = queue.removeFirst();

                for (int i = 0; i < graph[fifo].size(); i++) {
                    int neighbour = graph[fifo].get(i);
                    if (dist[neighbour] == -1) {
                        dist[neighbour] = dist[fifo] + 6;
                        queue.addLast(neighbour);
                    }
                }
            }

        }

    }


    static int countMaxRegion = 0;
    static int[][] DIRECTIONS = new int[][]{{1,1}, {-1,-1}, {1,-1}, {-1,1}, {0,1}, {0, -1}, {1,0}, {-1,0}};
    static int r,c;

    /*
     * Question : Find the maximum connected cells having value as 1.
     */
    static int maxRegion(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        boolean[][] visited = new boolean[r][c];

        int maxCount = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    countMaxRegion = 0;
                    dfs(grid, visited, i, j);
                    maxCount = Math.max(countMaxRegion, maxCount);
                }
            }
        }

        return maxCount;
    }

    private static void dfs(final int[][] grid, final boolean[][] visited, final int i, final int j) {
        if (i < 0 || j < 0 || i >= r || j >= c || grid[i][j] == 0 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        countMaxRegion++;

        for (int k = 0; k < DIRECTIONS.length; k++) {
            dfs(grid, visited, i + DIRECTIONS[k][0], j + DIRECTIONS[k][1]);
        }
    }


    static private int LIMIT = 100_001;
    static private int[] parent = new int[LIMIT];
    static private boolean[] checkMachines = new boolean[LIMIT];
    /*
     * Question : Find the minimum time it will take to destroy roads, such that no two machines
     * have a path between each other.
     *
     * [Approach 1 : Kruskal + Disjoint Set]
     * [Approach 2 : DP (below this method)]
     */
    private static int minTime(int[][] roads, int[] machines) {

        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getTime).reversed());

        for (int i = 0; i < roads.length; i++) {
            queue.add(new Edge(roads[i][0], roads[i][1], roads[i][2]));
        }

        Arrays.fill(parent, -1);
        Arrays.fill(checkMachines, false);

        for (int i = 0; i < machines.length; i++) {
            checkMachines[machines[i]] = true;
        }

        int totalCount = 0;

        while (!queue.isEmpty()) {
            Edge edge = queue.remove();
            if (checkMachines[findParent(edge.n1)] && checkMachines[findParent(edge.n2)]) {
                totalCount += edge.time;
            } else {
                union(edge.n1, edge.n2);
            }
        }

        return totalCount;
    }

    private static void union(final int a, final int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);
        if (checkMachines[parentA]) {
            parent[parentB] = parentA;
            return;
        }
        parent[parentA] = parentB;
    }

    private static int findParent(final int a) {
        if (parent[a] == -1) {
            return a;
        }
        return findParent(parent[a]);
    }

    private static class Edge {
        public int n1, n2, time;
        public Edge(final int n1, final int n2, final int time) {
            this.n1 = n1;
            this.n2 = n2;
            this.time = time;
        }

        public int getTime() {
            return this.time;
        }
    }

    public static void main(String[] args) {

        {
            System.out.println(Graphs.roadsAndLibraries(6, 2, 5, new int[][]{{1,2}, {3,1}, {2,3}, {2,4}, {3,4}, {5,6}}));
        }

        {
            System.out.println(Graphs.findShortest(4, new int[]{1,1,4}, new int[]{2,3,2}, new long[]{1,2,3,4}, 2));
        }

        {
            Graph graph = new Graph(7);
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(2, 3);
            graph.addEdge(1, 4);
            System.out.println(Arrays.toString(graph.shortestReach(1)));
        }

        {
            System.out.println(Graphs.maxRegion(new int[][]{{1,0,0,0}, {0,1,1,0}, {0,0,1,0}, {1,0,0,0}}));
        }

        {
            int[][] roads = new int[][] {
                    {2, 1, 8},
                    {1, 0, 5},
                    {2, 4, 5},
                    {1, 3, 4}
            };
            System.out.println("Minimum time to destroy roads : " + Graphs.minTime(roads, new int[]{2,4,0}));
        }
    }

}
