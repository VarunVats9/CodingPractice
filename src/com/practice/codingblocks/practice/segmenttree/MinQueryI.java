package com.practice.codingblocks.practice.segmenttree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MinQueryI {

    private static int[] temp, arr;

    private static int query(int index, int ql, int qr, int s, int e) {

        // Base case
        if (s > e) return Integer.MAX_VALUE;

        // No overlap
        if (s > qr || e < ql) return Integer.MAX_VALUE;

        // Overlap
        if (s >= ql && e <= qr) return temp[index];

        // Partial
        int mid = (s+e)/2;
        int left = query(2*index, ql, qr, s, mid);
        int right = query(2*index+1, ql, qr, mid+1, e);

        return Math.min(left, right);
    }

    private static void build(int index, int s, int e) {
        // Base case
        if (s > e) return;

        // Base case
        if (s == e) {
            temp[index] = arr[s];
            return;
        }

        int mid = (s+e)/2;

        // recurse left side
        build(2*index, s, mid);

        // recurse right side
        build(2*index+1, mid+1, e);

        // Update the parent
        temp[index] = Math.min(temp[2*index], temp[2*index+1]);
    }

    private static void update(int index, int i, int value, int s, int e) {

        // Base case
        if (s == e && s == i) {
            arr[i] = value;
            temp[index] = value;
            return;
        }

        // Base case
        if (s >= e) return;

        int mid = (s+e)/2;

        if (s <= i && mid >= i) {
            update(2 * index, i, value, s, mid);
        } else {
            update(2 * index + 1, i, value, mid + 1, e);
        }

        temp[index] = Math.min(temp[2*index], temp[2*index+1]);
    }

    public static void main(String[] args) {
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            line = bufferedReader.readLine();
            String[] str = line.trim().split("\\s+");
            int N = Integer.parseInt(str[0]);
            int Q = Integer.parseInt(str[1]);
            arr = new int[N];
            line = bufferedReader.readLine();
            str = line.trim().split("\\s+");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            temp = new int[4*N + 1];
            build(1, 0, N-1);
            while (Q-- > 0) {
                line = bufferedReader.readLine();
                str = line.trim().split("\\s+");
                int q = Integer.parseInt(str[0]);
                if (q == 1) {
                    int l = Integer.parseInt(str[1]) - 1;
                    int r = Integer.parseInt(str[2]) - 1;
                    System.out.println(query(1, l, r, 0, N-1));
                } else {
                    int x = Integer.parseInt(str[1]) - 1;
                    int y = Integer.parseInt(str[2]);
                    update(1, x, y, 0, N-1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
