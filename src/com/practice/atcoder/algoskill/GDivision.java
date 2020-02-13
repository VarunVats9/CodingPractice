package com.practice.atcoder.algoskill;

import java.util.*;
import java.io.PrintWriter;

public class GDivision {

    static private int[] arr = new int[100];
    static private int sum, n;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        sum = Integer.MIN_VALUE;
        n = in.nextInt();

        for (int i = 0; i <= n-1; i++) {
            for (int j = 0; j <= n-1; j++) {
                if (i < j) {
                    arr[Integer.parseInt(i+""+j)] = in.nextInt();
                }
            }
        }

        int max = (int) Math.pow(3, n);
        int sum = Integer.MIN_VALUE;
        for (int i = 1; i <= max; i++) {
            sum = Math.max(sum, groupMax(i));
        }
        out.println(sum);
    }

    private int groupMax(int num) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new ArrayList<>());
        }
        return divideIntoGroup(num, list);
    }

    private int divideIntoGroup(int num, List<List<Integer>> list) {
        for (int i = 0; i < n; i++) {
            list.get(num % 3).add(i);
            num = num / 3;
        }

        int sum = 0;
        for (List<Integer> l : list) {
            for (int i = 0; i < l.size()-1; i++) {
                for (int j = 1; j < l.size(); j++) {
                    sum += arr[Integer.parseInt(l.get(i)+""+l.get(j))];
                }
            }
        }

        return sum;
    }

}
