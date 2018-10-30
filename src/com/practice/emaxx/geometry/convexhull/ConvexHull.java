package com.practice.emaxx.geometry.convexhull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.practice.emaxx.geometry.Pair;

/**
 * Created by vvats on 28/10/18.
 */
public class ConvexHull {

    public static void main(String[] args) {

        List<Pair<Double, Double>> points = new ArrayList<>();

        // Setup points.
        points.add(new Pair<>(0.0, 3.0));
        points.add(new Pair<>(1.0, 1.0));
        points.add(new Pair<>(2.0, 2.0));
        points.add(new Pair<>(4.0, 4.0));
        points.add(new Pair<>(0.0, 0.0));
        points.add(new Pair<>(1.0, 2.0));
        points.add(new Pair<>(3.0, 1.0));
        points.add(new Pair<>(3.0, 3.0));
        points.add(new Pair<>(3.0, 10.0));
        points.add(new Pair<>(2.10, 2.45));
        points.add(new Pair<>(2.20, 2.35));
        points.add(new Pair<>(2.30, 2.25));
        points.add(new Pair<>(2.40, 2.15));

        // Sort the points, based on x, and lower y.
        points.sort((p1, p2) -> {
            final int cmp = p1.getLeft().compareTo(p2.getLeft());
            if (cmp == 0) {
                return p1.getRight().compareTo(p2.getRight());
            }
            return cmp;
        });

        // leftmost -> A point, rightmost -> B point
        Pair<Double, Double> leftmost = points.get(0), rightmost = points.get(points.size() - 1);

        // Points above line A-B
        ArrayList<Pair<Double, Double>> upperStack = new ArrayList<>();

        // Points below line A-B
        ArrayList<Pair<Double, Double>> lowerStack = new ArrayList<>();

        lowerStack.add(leftmost);
        upperStack.add(leftmost);

        for (final Pair<Double, Double> pair : points) {

            if (pair == rightmost || cw(leftmost, pair, rightmost)) {

                // Check the stack size, and if the three points are not clockwise, then remove.
                while (upperStack.size() >= 2 && !(cw(upperStack.get(upperStack.size()-2), upperStack.get(upperStack.size()-1), pair))) {
                    upperStack.remove(upperStack.size()-1);
                }

                upperStack.add(pair);
            }

            if (pair == rightmost || ccw(leftmost, pair, rightmost)) {

                // Check the stack size, and if the three points are not counter clockwise, then remove.
                while (lowerStack.size() >= 2 && !(ccw(lowerStack.get(lowerStack.size()-2), lowerStack.get(lowerStack.size()-1), pair))) {
                    lowerStack.remove(lowerStack.size() - 1);
                }

                lowerStack.add(pair);
            }

        }

        Set<Pair<Double, Double>> set = new HashSet<>();
        set.addAll(upperStack);
        set.addAll(lowerStack);

        System.out.println("Convex Hull points : " + set);

    }

    private static boolean cw(final Pair<Double, Double> a, final Pair<Double, Double> b, final Pair<Double, Double> c) {
        /*
            slope (b, a) - slope (c, b) > 0

            1. (b.y - a.y) / (b.x - a.x) = (c.y - b.y) / (c.x - b.x) (when all are collinear)
            2. Cross multiply
            3. (c.x - b.x) * (b.y - a.y) = (b.x - a.x) * (c.y - b.y)
            4. a.x * (c.y - b.y) + b.x * (- b.y + a.y - c.y + b.y) + c.x * (b.y - a.y) > 0
            5. - (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) > 0
            6. (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) < 0
        */
        return a.getLeft() * (b.getRight() - c.getRight()) + b.getLeft() * (c.getRight() - a.getRight())
                + c.getLeft() * (a.getRight() - b.getRight()) < 0;

    }

    private static boolean ccw(final Pair<Double, Double> a, final Pair<Double, Double> b, final Pair<Double, Double> c) {
        return a.getLeft() * (b.getRight() - c.getRight()) + b.getLeft() * (c.getRight() - a.getRight())
                + c.getLeft() * (a.getRight() - b.getRight()) > 0;
    }
}
