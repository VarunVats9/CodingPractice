package com.practice.atcoder.abc151;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;

public class CWelcomeToAtCoder {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();

        // Skip the extra newline
        in.nextLine();

        Map<Integer, Pair> map = new HashMap<>();

        for (int i = 1; i <= M; i++) {
            String l = in.nextLine();
            String[] token = l.split(" ");
            Integer p = Integer.parseInt(token[0]);
            String sub = token[1];

            if (map.containsKey(p)) {
                if (!map.get(p).succeed)
                    if (sub.equals("WA"))
                        map.put(p, new Pair(map.get(p).count + 1, false));
                    else
                        map.put(p, new Pair(map.get(p).count, true));
            } else {
                if (sub.equals("WA"))
                    map.put(p, new Pair(1, false));
                else
                    map.put(p, new Pair(0, true));
            }
        }


        long pen = 0;
        long cor = 0;

        for (Map.Entry<Integer, Pair> entry : map.entrySet()) {
            if (entry.getValue().succeed) {
                cor += 1;
                pen += entry.getValue().count;
            }
        }


        out.println(cor + " " + pen);
    }

    static class Pair {
        int count;
        boolean succeed;
        public Pair(int c, boolean s) {
            count = c;
            succeed = s;
        }
    }
}
