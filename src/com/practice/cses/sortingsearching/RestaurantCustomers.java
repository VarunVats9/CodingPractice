package com.practice.cses.sortingsearching;

import com.practice.fastio.InputReader;
import org.omg.CORBA.INTERNAL;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantCustomers {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer> s = new ArrayList<>(n);
        List<Integer> e = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            s.add(in.nextInt());
            e.add(in.nextInt());
        }

        Collections.sort(s);
        Collections.sort(e);

        int ei = 0, sj = 0, count = 0, mc = Integer.MIN_VALUE;


        while (ei < n && sj < n) {
            if (s.get(sj) < e.get(ei)) {
                count++;
                mc = Math.max(mc, count);
                sj++;
            } else {
                count--;
                ei++;
            }
        }

        out.println(mc);
    }
}
