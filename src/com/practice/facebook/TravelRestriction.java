package com.practice.facebook;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author varunvats32
 */
public class TravelRestriction {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream("/Users/v0v00bl/IdeaProjects/home-work/practice/src/com/practice/chelper/in.txt"));
        PrintWriter out = new PrintWriter(new FileOutputStream("/Users/v0v00bl/IdeaProjects/home-work/practice/src/com/practice/chelper/out.txt"));
        Solver solver = new Solver();
        solver.solve(1, in, out);
        out.close();
    }

    static class Solver {

        char[][] mat = null;
        char[] I = null;
        char[] O = null;

        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int t = in.nextInt();

            for (int i = 0; i < t; i++) {
                int n = in.nextInt();

                in.nextLine();

                mat = new char[n][n];

                for (char[] row : mat) {
                    Arrays.fill(row, 'N');
                }

                I = in.nextLine().toCharArray();
                O = in.nextLine().toCharArray();

                for (int l = 0; l < n; l++) {
                    mat[l][l] = 'Y';
                }

                for (int j = 0; j < n; j++) {
                    if (O[j] == 'N') continue;
                    updateBefore(j);
                    updateAfter(j);
                }

                validateAllOtherCountriesBefore();
                validateAllOtherCountriesAfter();

                out.println("Case #" + (i+1) + ":");
                for (int p = 0; p < n; p++) {
                    for (int q = 0; q < n; q++) {
                        out.print(mat[p][q]);
                    }
                    out.println();
                }

            }


        }

        private void validateAllOtherCountriesBefore() {
            for (int p = 0; p < I.length; p++) {
                if (O[p] == 'N') continue;
                for (int q = p-2; q >= 0; q--) {
                    boolean ans = I[q] == 'Y' && (mat[q+2][q+1] == 'Y') && (mat[q+1][q] == 'Y');
                    if (!ans) break;
                    else mat[p][q] = 'Y';
                }
            }
        }

        private void validateAllOtherCountriesAfter() {
            for (int p = 0; p < I.length; p++) {
                if (O[p] == 'N') continue;
                for (int q = p+2; q < I.length; q++) {
                    boolean ans = I[q] == 'Y' && (mat[q-2][q-1] == 'Y') && (mat[q-1][q] == 'Y');
                    if (!ans) break;
                    else mat[p][q] = 'Y';
                }
            }
        }

        private void updateAfter(int j) {
            if (j == I.length - 1) return;
            mat[j][j+1] = I[j+1] == 'N' ? 'N' : 'Y';
        }

        private void updateBefore(int j) {
            if (j == 0) return;
            mat[j][j-1] = I[j-1] == 'N' ? 'N' : 'Y';
        }

    }
}


