package com.practice.algorithmslive.episode0;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author varunvats32
 */
// https://www.spoj.com/problems/INVCNT/
public class Sample_InversionCount {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        InversionCount solver = new InversionCount();
        solver.solve(1, in, out);
        out.close();
    }

    static class InversionCount {
        public void solve(int testNumber, Scanner in, PrintWriter out) {
            int t = in.nextInt();

            while (t-- > 0) {
                int n = in.nextInt();
                int[] arr = new int[n + 1];
                long count = 0;
                int max = 0;
                for (int i = 1; i <= n; i++) {
                    arr[i] = in.nextInt();
                    max = Math.max(arr[i], max);
                }
                InversionCount.BIT bit = new InversionCount.BIT(max + 1);
                for (int i = 1; i <= n; i++) {
                    int x = arr[i];
                    count += bit.rangeSum(x + 1, max);
                    bit.update(x, 1);
                }
                out.println(count);
            }
        }

        public static class BIT {
            private int size;
            private long[] table;

            public BIT(int size) {
                this.size = size;
                table = new long[size];
            }

            public long sum(int i) {
                long sum = 0;
                while (i > 0) {
                    sum += table[i];
                    i -= Long.lowestOneBit(i);
                }
                return sum;
            }

            public void update(int i, int delta) {
                while (i < size) {
                    table[i] += delta;
                    i += Long.lowestOneBit(i);
                }
            }

            public long rangeSum(int i, int j) {
                return sum(j) - sum(i - 1);
            }

        }

    }
}


