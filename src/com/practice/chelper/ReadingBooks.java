package com.practice.chelper;

import com.practice.egork.lib.generated.collections.pair.IntIntPair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class ReadingBooks {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = in.readIntArray(n);
        Arrays.sort(arr);

        int s = 0, e = arr.length - 1;
        int fp = s, sp = e;
        long ft = 0, st = 0;
        while (fp > e && sp < s) {
            if (fp != sp) {
                ft += arr[fp];
                st += arr[sp];
                fp++; sp--;
            } else {

            }
        }

        out.println(Math.max(ft, st));
    }
}
