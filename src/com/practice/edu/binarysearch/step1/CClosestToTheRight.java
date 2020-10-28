package com.practice.edu.binarysearch.step1;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;

public class CClosestToTheRight {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();

        int[] arr = in.readIntArray(n);

        while (q-- > 0) {
            int l = -1, r = n;
            int x = in.nextInt();
            while (r - l > 1) {
                int m = (l + r) / 2;
                if (arr[m] < x) l = m;
                else r = m;
            }
            out.println(r + 1);
        }
    }
}
