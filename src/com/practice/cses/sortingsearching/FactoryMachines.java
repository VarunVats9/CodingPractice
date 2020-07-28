package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FactoryMachines {

    List<Integer> arr = null;
    long max = (long) 1e18;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        arr = in.readIntegerArray(n);

        long l = 0, h = max;
        while (l < h) {
            long mid = (l+h) / 2;
            if (isOk(t, mid)) {
                h = mid;
            } else {
                l = mid+1;
            }
        }

        out.println(l);
    }

    private boolean isOk(long products, long maxTime) {
        int countProducts = 0;
        for (int i = 0; i < arr.size(); i++) {
            countProducts += Math.min(maxTime / arr.get(i), 1e9);
        }
        return countProducts >= products;
    }
}
