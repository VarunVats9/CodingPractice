package com.practice.codingblocks.practice.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class QuickSort {

    private static int[] arr;

    private static void quicksort(int s, int e) {

        // Base case
        if (s >= e) return;

        // Partition the array
        int p = partition(s, e);

        // Recursively call left and right side
        quicksort(s, p-1);
        quicksort(p+1, e);
    }

    private static int partition(int s, int e) {
        int pivot = arr[e];
        int p = s;

        for (int i = s; i <= e-1; i++) {
            if (arr[i] < pivot) {
                swap(p, i);
                p++;
            }
        }
        swap(e, p);
        return p;
    }

    private static void randomize() {
        Random r = new Random();
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            int random = r.nextInt(n);
            swap(i, random);
        }
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int n = Integer.parseInt(line);
            line = bufferedReader.readLine();
            String[] str = line.trim().split("\\s+");
            arr = new int[n];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            randomize();
            quicksort(0, n-1);
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
