package com.practice.geeksforgeeks.dp.intermediate;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by vvats on 08/11/18.
 */
public class BuildingBridges {


    private static void buildingBridges(final int[] northern, final int[] southern) {

        int n = northern.length;
        Bridge[] bridges = new Bridge[n];

        for (int i = 0; i < n; i++) {
            bridges[i] = new Bridge(northern[i], southern[i]);
        }

        /*
         * Sort the bridges based on northern co-ordinates.
         */
        Arrays.sort(bridges, (o1, o2) -> {
            if (Objects.equals(o1.getN(), o2.getN())) {
                return o1.getS().compareTo(o2.getS());
            }
            return o1.getN().compareTo(o2.getN());
        });

        /*
         * dp[i], it stores the maximum bridges formed till 'i'.
         */
        int[] dp = new int[n+1];

        int maxBridges = 0;


        /*
         * Every bridge can be formed with just its own co-ordinates.
         */
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {

                /*
                 * Modified LIS, in case of two bridges (a, b) and (c, d).
                 * Since a and c are already sorted, we just need to take care of southern co-ordinates.
                 * That is, only b <= d, to get two non-crossing bridges.
                 */
                if (bridges[j-1].getS() >= bridges[i-1].getS()
                        && dp[j] < dp[i] + 1) {
                    dp[j] = dp[i] + 1;
                    maxBridges = Math.max(maxBridges, dp[j]);
                }
            }
        }


        System.out.println("Maximum number of bridges : " + maxBridges);
    }

    private static class Bridge {

        private int n, s;

        public Integer getN() {
            return this.n;
        }

        public Integer getS() {
            return this.s;
        }

        public Bridge(final int n, final int s) {
            this.n = n;
            this.s = s;
        }
    }

    public static void main(String[] args) {

        {
            BuildingBridges.buildingBridges(new int[]{6, 4, 2, 1}, new int[]{2, 3, 6, 5});
        }

        {
            BuildingBridges.buildingBridges(new int[]{8, 1, 4, 3, 5, 2, 6, 7}, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        }

    }
}
