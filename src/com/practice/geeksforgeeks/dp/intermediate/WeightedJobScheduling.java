package com.practice.geeksforgeeks.dp.intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * Variation of LIS.
 */
public class WeightedJobScheduling {

    private static void wjs(final List<Job> jobs) {

        int n = jobs.size();

        jobs.sort(Comparator.comparing(Job::getStart));

        // dp[i], it means that till ith position, it stores the maximum profit earned.
        int[] dp = new int[n+1];

        int maxProfit = 0;

        // Profit for each job, is its current position value.
        for (int j = 1; j <= n; j++) {
            dp[j] = jobs.get(j-1).profit;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                /*
                 * Variation of LIS, since both jobs cannot overlap, need to check the finish of first
                 * is less than start of second. And, while calculating the profit for a particular position,
                 * if the profit comes out to be more than the previous stored value, we update.
                 */
                if (jobs.get(j-1).start >= jobs.get(i-1).finish && dp[j] < dp[i] + jobs.get(j-1).profit) {
                    dp[j] = dp[i] + jobs.get(j-1).profit;
                    maxProfit = Math.max(maxProfit, dp[j]);
                }
            }
        }

        System.out.println("Maximum profit accumulated with Weighted Job Scheduling is : " + maxProfit);

    }

    private static class Job {
        int start, finish, profit;

        public Job(final int start, final int finish, final int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }

        public Integer getStart() {
            return this.start;
        }
    }

    public static void main(String[] args) {

        {
            WeightedJobScheduling.wjs(Arrays.asList(new Job(3, 10, 20),
                    new Job(1, 2, 50),
                    new Job(6, 19, 100),
                    new Job(2, 100, 200)));
        }

    }

}
