package com.practice.geeksforgeeks.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import com.practice.emaxx.geometry.Pair;

/**
 * Created by vvats on 04/11/18.
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

        // Open and close list.
        Queue<Pair<Pair<Integer, Integer>, Double>> open = new PriorityQueue<>(Comparator.comparingDouble(Pair::getRight));

        Map<Pair<Integer, Integer>, Boolean> close = new HashMap<>();

        // CurrentNode ---> Parent
        Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent = new HashMap<>();

        // Adding source into the open list.
        open.add(new Pair<Pair<Integer, Integer>, Double>(source, 0.0));

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
                if (!isValidNeighbour(neighbour, grid)) {
                    continue;
                }

                // Neighbour is the dest.
                if (neighbour.equals(dest)) {
                    updateParent(parent, currentNode, neighbour);
                    return;
                }

                final Double neighbourF = calculateF(neighbour, dest, currentNode);
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

        printPathFromDestToSource(source, dest, parent);
    }

    private static void printPathFromDestToSource(final Pair<Integer, Integer> source, final Pair<Integer, Integer> dest, final Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent) {

        Pair<Integer, Integer> current = dest;

        while (!parent.get(current).equals(source)) {
            System.out.println("Going from " + current + "  ---->  " + parent.get(current));
            current = parent.get(current);
        }

    }

    private static void updateParent(final Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent, final Pair<Integer, Integer> currentNode, final Pair<Integer, Integer> neighbour) {
        if (parent.containsKey(neighbour)) {
            parent.put(neighbour, currentNode);
        } else {
            parent.put(neighbour, new Pair<Integer, Integer>(currentNode));
        }
    }

    private static Double findInOpen(final Pair<Integer, Integer> neighbour, final Queue<Pair<Pair<Integer, Integer>, Double>> open) {

        final Optional<Pair<Pair<Integer, Integer>, Double>> any = open.stream()
                .filter(e -> e.getLeft().equals(neighbour))
                .findAny();

        return any.map(Pair::getRight).orElse(null);
    }

    private static Double calculateF(final Pair<Integer, Integer> neighbour, final Pair<Integer, Integer> dest, final Pair<Integer, Integer> currentNode) {
        final Double g = currentNode.getRight() + calculateEuclidean(neighbour, currentNode);
        final Double h = calculateEuclidean(neighbour, dest);
        return g + h;
    }

    private static Double calculateEuclidean(final Pair<Integer, Integer> neighbour, final Pair<Integer, Integer> currentNode) {
        final Double x = (neighbour.getLeft() - currentNode.getLeft()) * (neighbour.getLeft() - currentNode.getLeft()) * 1.0;
        final Double y = (neighbour.getRight() - currentNode.getRight()) * (neighbour.getRight() - currentNode.getRight()) * 1.0;
        return Math.sqrt(x + y);
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
            { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 }
        };

        // Source (8, 0) and Destination (0, 0).
        AStarSearch.search(new Pair<Integer, Integer>(8, 0), new Pair<Integer, Integer>(0, 0), grid);
    }

}
