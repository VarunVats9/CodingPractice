package com.practice.geeksforgeeks.dp.intermediate;

/**
 * Created by vvats on 15/11/18.
 */
public class WildcardMatching {


    private static void match(final String text, final String pattern) {

        int n = text.length();
        int m = pattern.length();

        boolean[][] dp = new boolean[n+1][m+1];

        /*
         * Case where pattern '*' is matched with empty text, then dp[i][j] is set to true.
         * Otherwise set the value to false.
         */
        for (int j = 1; j <= m; j++) {
            if (pattern.charAt(j-1) == '*') {
                dp[0][j] = true;
                continue;
            }
            break;
        }

        /*
         * Set the value to true for empty text and empty pattern.
         */
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                /*
                 * Case where the pattern and text char matches, or when the pattern '?' is matched with
                 * the current text char, then set the value to what it was for one less char in both the
                 * text and pattern. (i.e for dp[i-1][j-1]).
                 */
                if (pattern.charAt(j-1) == '?' || pattern.charAt(j-1) == text.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }

                /*
                 * In case of '*', two cases arises:
                 *
                 * 1. When '*' is considered to be empty : dp[i-1][j].
                 * 2. When '*' can matches to more chars, so instead of getting the answer from
                 *    dp[i-1][j-1], which means it matched the current char. We get the answer from the
                 *    previous row dp[i-1][j], which means we can extend the answer from previous '*' matcher.
                 *
                 * E.g. ba*d matches bashjfgrd, above point 2, means shjfgr matches to '*'.
                 */
                if (pattern.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                    continue;
                }

                dp[i][j] = false;
            }
        }

        System.out.println("Does the wildcard matching worked : " + dp[n][m]);

    }

    public static void main(String[] args) {

        {
            WildcardMatching.match("aaabb", "a*b");
        }

        {
            WildcardMatching.match("baaabab", "*****ba*****ab");
        }

        {
            WildcardMatching.match("baaabab", "baaa?ab");
        }

        {
            WildcardMatching.match("baaabab", "ba*a?");
        }

        {
            WildcardMatching.match("baaabab", "a*ab");
        }

    }

}
