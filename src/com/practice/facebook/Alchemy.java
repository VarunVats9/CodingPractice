package com.practice.facebook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author varunvats32
 */
public class Alchemy {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream("/Users/v0v00bl/IdeaProjects/home-work/practice/src/com/practice/chelper/in.txt"));
        PrintWriter out = new PrintWriter(new FileOutputStream("/Users/v0v00bl/IdeaProjects/home-work/practice/src/com/practice/chelper/out.txt"));
        Solver solver = new Solver();
        solver.solve(1, in, out);
        out.close();
    }

    static class Solver {

        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                int x = in.nextInt();
                in.nextLine();
                char[] l = in.nextLine().toCharArray();
                int count = 0;
                for (int j = 0; j < l.length; j++) {
                    if (l[j] == 'A') count++;
                }
                if (Math.abs(2*count - l.length) == 1) out.println("Case #" + (i+1) + ": " + 'Y');
                else out.println("Case #" + (i+1) + ": " + 'N');
            }
        }

    }
}


