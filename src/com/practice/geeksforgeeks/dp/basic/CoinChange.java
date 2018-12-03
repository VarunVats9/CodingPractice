package com.practice.geeksforgeeks.dp.basic;

import java.util.Arrays;

/**
 * Date : 02 Dec, 2018
 * Time : 2:58 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class CoinChange {


    private static void change(final int[] coins, final int value) {

        int n = coins.length;
        int m  = value;

        /*
         * dp[i][j], it means how many ways that can be achieved to generate value 'j',
         * considering, the coins from 1 to 'i', can be used.
         *
         */
        int[][] dp = new int[n+1][m+1];

        /*
         * dp[i][0] : It means how many ways are there to achieve a value of '0',
         * considering the coins from 1 to 'i'.
         *
         * The answer is 1. That is don't choose at all.
         */
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                /*
                 * Excluding the coin, that is the just get the answer from the point where
                 * we are considering the coins from 1 to 'i-1'. Sum being the same.
                 */
                int exclude = dp[i-1][j];

                /*
                 * Including the coin, that is first include the coin, and now the sum has reduced,
                 * now just get that dp value of dp[coins used being the same][reduced sum].
                 *
                 * Since, we can use any change any number of time, hence we are using coins again from
                 * 1 to 'i'.
                 *
                 *
                 * NOTE (Important): In case you are considering filling up the dp table dp[coin value][coin value] = 1.
                 * This is wrong, as the dp[coin value][coin value] = 1, is correct only if coin value is 1.
                 * for dp[2][2] answer is 2. So, always rely on base case, and in case you feel you need to fill
                 * few fields prior, just check or validate those firsts, to be on safer side.
                 */
                int include = j-coins[i-1] >= 0 ? dp[i][j-coins[i-1]] : 0;

                dp[i][j] = exclude + include;
            }
        }

        System.out.println("Total ways in which given changes " + Arrays.toString(coins) +
                " can be used to generate value " + value + " is : " + dp[n][m]);

    }

    public static void main(String[] args) {
        {
            int[] coins = new int[]{1, 2, 3};
            int value = 4;
            CoinChange.change(coins, value);
        }

        {
            int[] coins = new int[]{2, 3, 5, 6};
            int value = 10;
            CoinChange.change(coins, value);
        }
    }

}
