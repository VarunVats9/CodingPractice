package com.practice.codingblocks.practice.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class NQueenI {

    private static BitSet col;
    private static BitSet dg, adg;
    private static int T;
    private static int sol = 0;

    private static void findQueenPlacement(int r) {

        // base case
        if (r == T+1) {
            sol++;
            return;
        }

        // recursive
        for (int c = 0; c <= T; c++) {
            if (!adg.get(r+c) && !dg.get(T+r-c) && !col.get(c)) {
                setValue(r, c);
                findQueenPlacement(r+1);
                unsetValue(r, c);
            }
        }
    }

    private static void setValue(int r, int c) {
        col.set(c);
        adg.set(r+c);
        dg.set(T+r-c);
    }

    private static void unsetValue(int r, int c) {
        col.clear(c);
        adg.clear(r+c);
        dg.clear(T+r-c);
    }

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            long start = System.currentTimeMillis();
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            col = new BitSet(n);
            T = n-1;
            adg = new BitSet(2*T+1);
            dg = new BitSet(2*T+1);
            findQueenPlacement(0);
            System.out.println(sol);
            //System.out.println("Total solutions : " + sol);
            //System.out.println("Total time taken :[" + (System.currentTimeMillis() - start)/ 1000 + "] seconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
