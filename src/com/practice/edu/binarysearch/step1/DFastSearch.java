package com.practice.edu.binarysearch.step1;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class DFastSearch {

    static int[] arr;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        arr = in.readIntArray(n);
        Arrays.sort(arr);

        int q = in.nextInt();
        while (q-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();

            int i = closestToRight(l);
            int j = closestToLeft(r);
            out.print((j - i + 1) + " ");
        }
    }

    private int closestToRight(int x) {
        int l = -1, r = arr.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (arr[m] < x) l = m;
            else r = m;
        }
        return (r + 1);
    }

    private int closestToLeft(int x) {
        int l = -1, r = arr.length;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (arr[m] > x) r = m;
            else l = m;
        }
        return (l + 1);
    }
}
