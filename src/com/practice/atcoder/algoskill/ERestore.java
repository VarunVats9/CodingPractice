package com.practice.atcoder.algoskill;

import java.util.*;
import java.io.PrintWriter;

public class ERestore {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String l = in.nextLine();
        String[] t = l.split(" ");
        int n = Integer.parseInt(t[0]);
        int q = Integer.parseInt(t[1]);

        Set[] sets = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            sets[i] = new HashSet<Integer>();
        }

        for (int i = 1; i <= q; i++) {
            String s = in.nextLine();
            String[] token = s.split(" ");
            if (token[0].equals("1")) {
                int a = Integer.parseInt(token[1]);
                int b = Integer.parseInt(token[2]);
                sets[a].add(b);
            } else if (token[0].equals("2")) {
                int a = Integer.parseInt(token[1]);
                Set<Integer> newValues = new HashSet<>();
                for (int j = 1; j <= n; j++) {
                    if (a == j) continue;
                    if (sets[j].contains(a)) {
                        newValues.add(j);
                    }
                }
                sets[a].addAll(newValues);
            } else {
                int a = Integer.parseInt(token[1]);
                Iterator it = sets[a].iterator();
                Set<Integer> newValues = new HashSet<>();
                while (it.hasNext()) {
                    newValues.addAll(sets[(Integer) it.next()]);
                }
                if (newValues.contains(a)) {
                    newValues.remove(a);
                }
                sets[a].addAll(newValues);
            }

        }

        char[][] mat = new char[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(mat[i], 'N');
            for (Object o : sets[i]) {
                int j = (Integer) o;
                mat[i][j] = 'Y';
            }
        }

        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder("");
            for (int j = 1; j <= n; j++) {
                sb.append(mat[i][j]);
            }
            out.println(sb.toString());
        }
    }
}
