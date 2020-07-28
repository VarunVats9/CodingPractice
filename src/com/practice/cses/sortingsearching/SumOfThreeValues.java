package com.practice.cses.sortingsearching;

import com.practice.egork.lib.generated.collections.pair.IntIntPair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class SumOfThreeValues {

    List<IntIntPair> list = null;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(IntIntPair.makePair(i+1, in.nextInt()));
        }

        Collections.sort(list, Comparator.comparingInt(a -> a.second));
        for (int i = 0; i < n; i++) {
            IntIntPair pair = found(i+1, t - list.get(i).second);
            if (Objects.nonNull(pair)) {
                out.print(list.get(i).first + " " + pair.first + " " + pair.second);
                return;
            }
        }

        out.println("IMPOSSIBLE");
    }

    private IntIntPair found(int i, int t) {
        IntIntPair pair = null;
        int l = i, h = list.size() - 1;
        while (l < h) {
            long sum = list.get(l).second + list.get(h).second;
            if (sum == t) {
                return IntIntPair.makePair(list.get(l).first, list.get(h).first);
            }
            if (sum < t) {
                l++;
            } else {
                h--;
            }
        }

        return pair;
    }
}
