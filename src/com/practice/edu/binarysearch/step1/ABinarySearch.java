package com.practice.edu.binarysearch.step1;

import com.practice.fastio.InputReader;

import java.io.PrintWriter;

public class ABinarySearch {

    static int[] arr;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();

        arr = in.readIntArray(n);
        while (q-- > 0) {
            if (bSearch(in.nextInt())) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    private boolean bSearch(int x) {
        int n = arr.length;
        int l = -1, r = n;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (arr[m] < x) l = m;
            else r = m;
        }
        return (r < n && arr[r] == x);
    }
}
