package com.practice.atcoder.educationaldp;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;

public class FLCS {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.nextLine();
        String t = in.nextLine();

        int n = s.length(), m = t.length();
        int[][] dp = new int[n+1][m+1];

        /*
        * 0 -> came from (i-1, j-1)
        * 1 -> came from (i-1, j)
        * 2 -> came from (i, j-1)
        * */
        int[][] choice = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    choice[i][j] = 0;
                } else {
                    if (dp[i-1][j] > dp[i][j-1]) {
                        dp[i][j] = dp[i-1][j];
                        choice[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i][j-1];
                        choice[i][j] = 2;
                    }
                }
            }
        }

        int i = n, j = m;
        StringBuilder ans = new StringBuilder("");
        while (i > 0 && j > 0) {
            if (choice[i][j] == 0) {
                ans.append(s.charAt(i-1));
                i--; j--;
            } else if (choice[i][j] == 1) {
                i--;
            } else {
                j--;
            }
        }

        out.println(ans.reverse().toString());
    }
}
