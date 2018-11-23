package com.practice.geeksforgeeks.dp.hard;

/**
 * Date : 23 Nov, 2018
 * Time : 9:27 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class WordWrap {

    private final static int INFINITY = Integer.MAX_VALUE;

    private static void wrap(final int[] wordLengths, final int lineWidth) {

        int n = wordLengths.length;

        // dp[i][j], it means the cost of putting words from i to j in one line, of width m.
        int[][] dp = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = INFINITY;
            }
        }

        /*
         * For all the dp[i][i], that is the case where only that word is
         * there in the whole line. It will be (lineWidth - wordLength(i)) ^ 3.
         */
        for (int i = 1; i <= n; i++) {
            int extraSpace = lineWidth - wordLengths[i-1];
            dp[i][i] = (int)Math.pow(extraSpace, 3);
        }

        for (int i = 1; i <= n; i++) {

            /*
             * Since, dp[i][i], has already been calculated, so we will
             * consider cases only j > i, and since dp[i][i] has taken some
             * space will update that as well, before starting the j loop.
             */
            int extraSpace = lineWidth - wordLengths[i-1];
            for (int j = i+1; j <= n; j++) {

                /*
                 * Each word is preceded by a space, hence taken that into
                 * consideration, and if the extraSpace is insufficient to include the word,
                 * will update the dp[i][j] as INFINITY.
                 */
                int newWord = 1 + wordLengths[j-1];
                if (extraSpace < newWord) {
                    dp[i][j] = INFINITY;
                    continue;
                }

                /*
                 * Update the extraSpace, and dp[i][j].
                 */
                extraSpace = extraSpace - newWord;
                dp[i][j] = (int)Math.pow(extraSpace, 3);
            }
        }

        // cost[i], it means the minimum cost till ith word.
        int[] cost = new int[n+1];

        // Since there is no loop for j = 1, as it would just mean first word in one line.
        cost[1] = dp[1][1];

        /*
         * Recurrence rule :
         *
         * cost[j] = Min (cost[j], cost[i] + dp[i+1][j]) 1 <= i < j
         *
         * Example : j = 3,
         * cost[1] + dp[2][3]
         * cost[2] + dp[3][3]
         *
         */
        for (int j = 2; j <= n; j++) {

            // Initializing the cost[j] as INFINITY.
            cost[j] = INFINITY;
            for (int i = 1; i < j; i++) {

                /*
                 * Protection from integer overflow cases. And there is no use of continuing
                 * further as dp[i+1][j] cannot be wrapped in one line.
                 */
                if (dp[i+1][j] == INFINITY) {
                    continue;
                }

                cost[j] = Math.min(cost[j], cost[i] + dp[i+1][j]);
            }
        }

        System.out.println("Minimum cost of word wrapping is : " + cost[n]);
    }

    public static void main(String[] args) {

        {
            int[] l = new int[]{3, 2, 2, 5};
            int M = 6;
            WordWrap.wrap(l, M);
        }
    }

}
