package com.practice.hackerrank.interviewpreparationkit.arrays;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;


/**
 * Date : 01 Dec, 2018
 * Time : 3:57 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class Arrays {

    /*
     * Question : Calculate the maximum sum, over the array from 1 to n.
     * Given that queries of the following form has taken place on this array.
     * On range [a, b] : Add k to each value.
     */
    static long arrayManipulation(int n, int[][] queries) {
        List<AbstractMap.SimpleEntry<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            int k = queries[i][2];

            list.add(new AbstractMap.SimpleEntry<>(a, k));
            if (b+1 <= n) {
                list.add(new AbstractMap.SimpleEntry<>(b+1, -1 * k));
            }
        }

        list.sort((o1, o2) -> {
            if (o1.getKey().equals(o2.getKey())) {
                return o1.getValue().compareTo(o2.getValue());
            }
            return o1.getKey().compareTo(o2.getKey());
        });

        long sum = 0, max = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getValue();
            max = Math.max(sum, max);
        }

        return max;
    }

    /*
     * Question : Rotate the array by d values.
     */
    static int[] rotLeft(int[] a, int d) {
        int n = a.length;
        d = d % n;
        if (d == 0) {
            return a;
        }
        reverse(a, 0, n-1);
        reverse(a, n-d, n-1);
        reverse(a, 0, n-d-1);

        return a;
    }

    private static void reverse(int[] a, int s, int e) {
        while (s < e) {
            int temp = a[s];
            a[s] = a[e];
            a[e] = temp;
            s++; e--;
        }
    }

    public static void main(String[] args) {

        Arrays.arrayManipulation(4, new int[][]{{2,3,603}, {1,1,286}, {4,4,882}});

        Arrays.rotLeft(new int[]{1,2,3,4,5}, 3);

    }
}
