package com.practice.atcoder.algoskill;

import java.util.Scanner;
import java.io.PrintWriter;

public class HBulkSelling {

    private static int n;
    private static int[] arr;
    private static long sum, odd, all, o, e;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        arr = new int[n+1];

        odd = Integer.MAX_VALUE; all = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            arr[i] = in.nextInt();
            all = Math.min(all, arr[i]);
            if (i % 2 == 1) {
                odd = Math.min(odd, arr[i]);
            }
        }

        int q = in.nextInt();
        in.nextLine();

        for (int i = 1; i <= q; i++) {
            String s = in.nextLine();
            String[] token = s.split(" ");
            switch (token[0]) {
                case "1":
                    op1(Integer.parseInt(token[1]), Integer.parseInt(token[2]));
                    break;
                case "2":
                    op2(Integer.parseInt(token[1]));
                    break;
                case "3":
                    op3(Integer.parseInt(token[1]));
                    break;
            }
        }

        out.println(sum);
    }

    private void op3(int a) {
        if (Math.min(all, odd) < a) return;
        all -= a;
        odd -= a;
        o += a;
        e += a;
        sum += n * a;
    }

    private void op2(int a) {
        if (odd < a) return;
        odd -= a;
        o += a;
        all = Math.min(all, odd);
        sum += (n+1)/2 * a;
    }

    private void op1(int x, int a) {
        if (arr[x] - ((x % 2 == 1) ? o : e) < a) return;
        arr[x] -= a;
        if (x % 2 == 1) {
            odd = Math.min(arr[x] - o, odd);
        } else {
            all = Math.min(arr[x] - e, all);
            all = Math.min(all, odd);
        }
        sum += a;
    }
}
