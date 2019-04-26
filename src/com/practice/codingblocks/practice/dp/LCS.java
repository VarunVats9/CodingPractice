package com.practice.codingblocks.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LCS {

    private static String a, b;
    private static int[][]dp;

    private static int recursiveLcs(int idxA, int idxB, int count) {
        if (idxA == a.length() || idxB == b.length()) {
            return count;
        }

        return (a.charAt(idxA) == b.charAt(idxB) ? recursiveLcs(idxA+1, idxB+1, count+1) :
                Math.max(recursiveLcs(idxA+1, idxB, count), recursiveLcs(idxA, idxB+1, count)));
    }

    private static int DpLcs(int idxA, int idxB) {
        if (idxA == a.length() || idxB == b.length()) {
            return 0;
        }

        if (dp[idxA][idxB] != -1) {
            return dp[idxA][idxB];
        }

        return dp[idxA][idxB] = (a.charAt(idxA) == b.charAt(idxB) ? 1 + DpLcs(idxA+1, idxB+1) :
                Math.max(DpLcs(idxA+1, idxB), DpLcs(idxA, idxB+1)));
    }

    public static void main(String[] args){

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            a = bufferedReader.readLine();
            b = bufferedReader.readLine();
            dp = new int[a.length()][b.length()];
            for (int[] row : dp)
                Arrays.fill(row, -1);
            System.out.println(recursiveLcs(0, 0, 0));
            System.out.println(DpLcs(0, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
