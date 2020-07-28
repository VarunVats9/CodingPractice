package com.practice.chelper;

import com.practice.fastio.InputReader;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DZeroRemainderArray {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt(), k = in.nextInt();
            Map<Integer, Integer> map = new HashMap<>();
            int mc = 0, num = -1;
            boolean allDivisible = true;
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                if (x % k != 0) allDivisible = false;
                map.putIfAbsent(x, 0);
                map.put(x, map.get(x) + 1);
                if (map.get(x) > mc) {
                    mc = map.get(x);
                    num = x;
                }
            }

            if (allDivisible) {
                out.println(0);
            } else {
                if (num <= k) {
                    out.println(mc * k - num + 1);
                } else {
                    out.println((mc + 1) * k - num + (mc - 1));
                }
            }
        }
    }
}
