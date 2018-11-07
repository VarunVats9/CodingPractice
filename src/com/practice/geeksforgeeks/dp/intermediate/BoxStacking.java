package com.practice.geeksforgeeks.dp.intermediate;

import java.util.Arrays;

/**
 * Created by vvats on 07/11/18.
 */
public class BoxStacking {


    private static void boxStacking(final Box[] boxes) {

        /*
         *
         * Question has following constraints :
         * Constraint 1 : Can use any number of instances for each box.
         * Constraint 2 : Only boxes with strictly lesser w and d can be stacked on top of it.
         *
         * So, we cannot use more than one box in one direction, hence change the orientation.
         *
         * Consider the box orientation in all three dimensions.
         * h * w * d, w >= d
         * 1 * (3 * 2)
         * 2 * (3 * 1)
         * 3 * (2 * 1)
         */

        final Box[] allOrientation = new Box[boxes.length * 3];

        for (int i = 0; i < boxes.length; i++) {
            allOrientation[3 * i + 0] = new Box(boxes[i].getH(), boxes[i].getW(), boxes[i].getD());
            allOrientation[3 * i + 1] = new Box(boxes[i].getW(), boxes[i].getH(), boxes[i].getD());
            allOrientation[3 * i + 2] = new Box(boxes[i].getD(), boxes[i].getW(), boxes[i].getH());
        }

        Arrays.sort(allOrientation, (o1, o2) -> o2.getBaseArea().compareTo(o1.getBaseArea()));

        int n = allOrientation.length;

        double[] dp = new double[n + 1];

        /*
         * dp[i], it means that how much height can be made with 1 to i boxes.
         *
         * Initially, all will have their own height.
         */
        for (int i = 1; i <= allOrientation.length; i++) {
            dp[i] = allOrientation[i-1].getH();
        }

        double maxHeight = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {

                /*
                 * Modified version of LIS, just the constraints are different.
                 */
                if (allOrientation[j-1].getW() < allOrientation[i-1].getW()
                        && allOrientation[j-1].getD() < allOrientation[i-1].getD()
                        && dp[j] < dp[i] + allOrientation[j-1].getH()) {
                    dp[j] = dp[i] + allOrientation[j-1].getH();
                    maxHeight = Math.max(maxHeight, dp[j]);
                }
            }
        }


        System.out.println("Highest height that can be achieved by box stacking is : " + maxHeight);
    }

    public static class Box {

        private double h, w, d;

        public double getH() {
            return this.h;
        }

        public double getW() {
            return this.w;
        }

        public double getD() {
            return this.d;
        }

        public Double getBaseArea() {
            return this.w * this.d;
        }

        Box(final double h, final double w, final double d) {
            this.h = h;

            /*
             * Keeping all the boxes with w >= d.
             * Ex. 1 * 2 * 3 (h * w * d) transformed to => 1 * 3 * 2
             */
            if (w >= d) {
                this.w = w;
                this.d = d;
            } else {
                this.w = d;
                this.d = w;
            }
        }
    }


    public static void main(String[] args) {

        {
            final Box[] boxes = new Box[4];
            boxes[0] = new Box(4, 6, 7);
            boxes[1] = new Box(1, 2, 3);
            boxes[2] = new Box(4, 5, 6);
            boxes[3] = new Box(10, 12, 32);

            BoxStacking.boxStacking(boxes);
        }

    }




}
