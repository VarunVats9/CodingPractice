package com.practice.codingblocks.livesessions;

import java.util.HashMap;
import java.util.HashSet;


public class SlidingWindow {

    private static int maximumWindowSum(int[] arr, int k) {

        if (k <= 0) {
            throw new IllegalArgumentException("Window size has to be greater than 0.");
        }

        int n = arr.length;
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            // Add all the elements to the sum variable.
            sum += arr[i];

            // For the first k elements, keep on adding.
            if (i < k) {

                // In case, we have added the sum for all the first k elements, update the max.
                if (i == k-1) max = sum;

                // No need to go after this, till i < k
                continue;
            }

            // After that, remove the element at (i-k)th position, from the sum.
            sum = sum - arr[i-k];

            // Keep track of the maximum sum window.
            if (sum > max) {
                max = sum;
            }

        }

        // Handling the case, when the array length is smaller than window size.
        return Math.max(max, sum);
    }

    private static void longestSubArrayKDistinctElements(int[] arr, int k) {

        // Maintaining a set to tell how many distinct elements are there.
        HashSet<Integer> windowSet = new HashSet<>();

        int n = arr.length;
        int s = 0, e = 0;

        // Final result
        int end = 0, len = 0;

        // Map <Element, And Its count>
        HashMap<Integer, Integer> map = new HashMap<>();

        while (e < n) {

            // Keep on adding to this set till the size is greater than k.
            while (windowSet.size() <= k && e < n) {
                populateMap(map, arr[e]);
                windowSet.add(arr[e]);
                e++;
            }

            // Now, we know, the probable answer can be from, s and e-1
            if ((e - 1) - s + 1 > len) {
                len = e - s;
                end = e - 1;
            }

            // Contract the set, again to the size of k, by removing elements from the 's' side.
            while (windowSet.size() != k) {
                int value = arr[s];

                // Remove this value from the map.
                map.put(value, map.get(value) - 1);

                // If the count for this map, has become zero, remove from set.
                if (map.get(value) == 0) {
                    windowSet.remove(value);
                }

                // Remove from map as well.
                map.remove(value);

                s++;
            }
        }

        // Print the array from start to end.
        System.out.print("Longest Sub-Array : ");
        for (int i = s; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();

    }

    private static void populateMap(HashMap<Integer, Integer> map, int key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    public static void main(String[] args) {

        System.out.println("Maximum Window Sum : " + maximumWindowSum(new int[]{8, 3}, 4));
        System.out.println("Maximum Window Sum : " + maximumWindowSum(new int[]{8, 3, 5, 2, 4, 5}, 3));

        longestSubArrayKDistinctElements(new int[]{1, 3, 2, 5, 2, 4}, 3);
        longestSubArrayKDistinctElements(new int[]{1, 2, 2, 5, 2, 4}, 4);
    }
}
