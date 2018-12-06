package com.practice.geeksforgeeks.search;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import com.practice.emaxx.geometry.Pair;

/**
 * Date : 04 Nov, 2018
 * Time : 6:18 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class AStarSearch {

    private static final int[][] DIRECTIONS = new int[][]{
            {0, 1},     // E
            {1, 0},     // S
            {0, -1},    // W
            {-1, 0},    // N
            {1, 1},     // S-E
            {-1, 1},    // N-E
            {1, -1},    // N-W
            {-1, -1}};  // S-W

    private static void search(final Pair<Integer, Integer> source, final Pair<Integer, Integer> dest, final int[][] grid) {

        /*
         * The algorithm, is kind of clever approach to solve a problem of finding the distance from
         * source to destination.
         *
         * We already know, DFS approach, but in this we keep track of distance from destination and source,
         * so that we know, we are finding in correct direction, and not going in random directions.
         *
         * 1. We keep two data structures, open (queue) and close (map)
         * 2. Few parameters, g : distance from source to parent cell, h : distance from destination to current cell.
         * 3. Open keeps the increasing order of 'f', for each cell (i,j), which we have visited.
         * 4. Cells already been visited, goes to close list.
         *
         * Algorithm goes something like this :
         * 1. Start with source (parent), and add all the neighbours' (current cell) with their 'f' values to open list.
         * 2. Every time remove the first cell from the open queue. If it is already present in close map, then continue.
         * 3. Again get all the neighbours, if there are cells, which are already there in open list but haven't been
         * visited, then check if it's f value can be improved, if so then update it.
         * 4. Update the parent once the current cell has been updated.
         */

        // Open and close list.
        Queue<Pair<Pair<Integer, Integer>, Double>> open = new PriorityQueue<>(Comparator.comparingDouble(Pair::getRight));

        Map<Pair<Integer, Integer>, Boolean> close = new HashMap<>();

        // CurrentNode ---> Parent
        Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent = new HashMap<>();

        // Adding source into the open list.
        open.add(new Pair<>(source, 0.0));

        while (!open.isEmpty()) {

            final Pair<Pair<Integer, Integer>, Double> lowestF = open.remove();

            final Pair<Integer, Integer> currentNode = lowestF.getLeft();

            // Already been evaluated, then skip.
            if (close.containsKey(currentNode)) {
                continue;
            }

            // Evaluation for it will be done now, adding early.
            close.put(currentNode, true);

            // Check all the neighbours for it.
            for (int i = 0; i < DIRECTIONS.length; i++) {

                final Pair<Integer, Integer> neighbour = evaluateNeighbour(currentNode, DIRECTIONS[i]);

                // Check if it is possible to add this neighbour.
                if (!isValidNeighbour(neighbour, grid) || close.containsKey(neighbour)) {
                    continue;
                }

                // Neighbour is the dest.
                if (neighbour.equals(dest)) {
                    updateParent(parent, currentNode, neighbour);
                    printPathFromDestToSource(source, dest, parent);
                    return;
                }

                final Double neighbourF = calculateF(source, neighbour, dest, currentNode);
                final Double ifNeighbourAlreadyInOpen = findInOpen(neighbour, open);

                // If the neighbour is valid and not there in open list, then add it || if it is doing better.
                if (Objects.isNull(ifNeighbourAlreadyInOpen) || ifNeighbourAlreadyInOpen > neighbourF) {
                    open.add(new Pair<>(neighbour, neighbourF));
                }

                // Skip if the node is not doing any better.
                if (!Objects.isNull(ifNeighbourAlreadyInOpen) && ifNeighbourAlreadyInOpen < neighbourF) {
                    continue;
                }

                updateParent(parent, currentNode, neighbour);
            }
        }

        System.out.println("No path is found between source and destination.");
    }

    private static void printPathFromDestToSource(final Pair<Integer, Integer> source,
            final Pair<Integer, Integer> dest, final Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent) {
        Pair<Integer, Integer> current = dest;
        Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();
        stack.addFirst(current);

        while (true) {
            current = parent.get(current);
            stack.addFirst(current);
            if (current.equals(source)) {
                break;
            }
        }

        while (!stack.isEmpty()) {
            final Pair<Integer, Integer> top = stack.removeFirst();
            if (top.equals(dest)) {
                return;
            }
            System.out.println("Going from " + top + "  ---->  " + stack.peekFirst());
        }
    }

    private static void updateParent(final Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent,
            final Pair<Integer, Integer> currentNode, final Pair<Integer, Integer> neighbour) {
        if (parent.containsKey(neighbour)) {
            parent.put(neighbour, currentNode);
        } else {
            parent.put(neighbour, new Pair<>(currentNode));
        }
    }

    private static Double findInOpen(final Pair<Integer, Integer> neighbour, final Queue<Pair<Pair<Integer, Integer>, Double>> open) {
        final Optional<Pair<Pair<Integer, Integer>, Double>> any = open.stream()
                .filter(e -> e.getLeft().equals(neighbour))
                .findAny();

        return any.map(Pair::getRight).orElse(null);
    }

    private static Double calculateF(final Pair<Integer, Integer> source, final Pair<Integer, Integer> neighbour, final Pair<Integer, Integer> dest, final Pair<Integer, Integer> currentNode) {
        final Double g = calculateEuclidean(currentNode, source) + calculateEuclidean(neighbour, currentNode);
        final Double h = calculateEuclidean(neighbour, dest);
        return g + h;
    }

    private static Double calculateEuclidean(final Pair<Integer, Integer> neighbour, final Pair<Integer, Integer> currentNode) {
        final Double x = (neighbour.getLeft() - currentNode.getLeft()) * 1.0;
        final Double y = (neighbour.getRight() - currentNode.getRight()) * 1.0;
        return Math.sqrt(x*x + y*y);
    }

    private static boolean isValidNeighbour(final Pair<Integer, Integer> neighbour, final int[][] grid) {
        // Going outside grid.
        if (neighbour.getLeft() < 0 || neighbour.getLeft() >= grid.length
                || neighbour.getRight() < 0 || neighbour.getRight() >= grid[0].length) {
            return false;
        }

        // Cell is blocked.
        return grid[neighbour.getLeft()][neighbour.getRight()] != 0;
    }

    private static Pair<Integer, Integer> evaluateNeighbour(final Pair<Integer, Integer> currentNode, final int[] direction) {
        return new Pair<>(currentNode.getLeft() + direction[0], currentNode.getRight() + direction[1]);
    }

    public static void main(String[] args) {
        /*
           Description of the Grid-
           1--> The cell is not blocked
           0--> The cell is blocked
        */
        int[][] grid = new int[][]
        {
            { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
            { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
            { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
            { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
            { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
            { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 }
        };

        // Source (8, 0) and Destination (0, 0).
        AStarSearch.search(new Pair<Integer, Integer>(8, 0), new Pair<Integer, Integer>(0, 0), grid);

        // Source (0, 0) and Destination (8, 9).
        AStarSearch.search(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(8, 9), grid);
    }

}
