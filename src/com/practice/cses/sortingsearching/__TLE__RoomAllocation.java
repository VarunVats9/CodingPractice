package com.practice.cses.sortingsearching;

import com.practice.egork.lib.collections.Pair;
import com.practice.egork.lib.generated.collections.pair.IntIntPair;
import com.practice.fastio.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class __TLE__RoomAllocation {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] ans = new int[n];

        List<Pair<Integer, IntIntPair>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Pair.makePair(i, IntIntPair.makePair(in.nextInt(), in.nextInt())));
        }

        Collections.sort(arr, (a, b) -> {
            if (a.second.second > b.second.second) return 1;
            else if (a.second.second < b.second.second) return -1;
            return 0;
        });

        LinkedList<Pair<Integer, IntIntPair>> list = new LinkedList<>(arr);
        int rooms = 0;
        while (list.size() > 0) {
            rooms++;
            int prev = Integer.MIN_VALUE;
            for (int i = 0; i < list.size();) {
                if (list.get(i).second.first > prev) {
                    prev = list.get(i).second.second;
                    ans[list.get(i).first] = rooms;
                    list.remove(i);
                } else {
                    i++;
                }
            }
        }

        out.println(rooms);
        for (int i = 0; i < ans.length; i++) {
            out.print(ans[i] + " ");
        }
    }
}
