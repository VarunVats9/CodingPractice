package com.practice.algo.sort;

//import static com.practice.algo.sort.QuickSort.quickSort;

//import java.util.Scanner;

import java.util.Objects;

import com.practice.hackerrank.interviewpreparationkit.arrays.Arrays;

/**
 * Date : 08 Jan, 2019
 * Time : 1:54 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class QuickSort {

    private static int[] arr;

    private static void quickSort(int low, int high) {

        if (low < high) {
            int pivot = partition(low, high);
            quickSort(low, pivot-1);
            quickSort(pivot + 1, high);
        }

    }

    private static int partition(int low, int high) {

        int pivot = high;

        int lesser = low;

        while (low < high) {

            if (arr[low] >= arr[pivot]) {
                low++;
            }

            if (arr[low] < arr[pivot]) {
                swap(low, lesser);
                low++;lesser++;
            }

        }

        swap(lesser, pivot);

        return lesser;
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main (String[] args) {
        /*Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int num = sc.nextInt();
            arr = new int[num];
            for (int i  = 0; i < num; i++) {
                arr[i] = sc.nextInt();
            }
            quickSort(0, arr.length - 1);
        }*/

        arr = new int[]{3,7,1,2,0,-4,1,4,6};

        quickSort(0, arr.length - 1);

        System.out.println(java.util.Arrays.toString(arr));

    }

    public class Holder {

        private Integer value;

        public Holder(final Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Holder holder = (Holder)o;
            return Objects.equals(value, holder.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

}
