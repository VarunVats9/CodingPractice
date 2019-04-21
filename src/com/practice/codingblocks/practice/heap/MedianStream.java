package com.practice.codingblocks.practice.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianStream {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int q = Integer.parseInt(line);
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int i = 1; i <= q; i++) {
                maxHeap.clear();
                minHeap.clear();
                line = bufferedReader.readLine();
                line = bufferedReader.readLine();
                String[] nums = line.split(" ");
                for (int j = 0; j < nums.length; j++) {
                    int x = Integer.parseInt(nums[j]);
                    if (maxHeap.size() == minHeap.size()) {
                        if (maxHeap.size() == 0) {
                            maxHeap.add(x);
                        } else {
                            int y = minHeap.peek();
                            if (x > y) {
                                minHeap.remove();
                                maxHeap.add(y);
                                minHeap.add(x);
                            } else {
                                maxHeap.add(x);
                            }
                        }
                        System.out.print(maxHeap.peek() + " ");
                    } else {
                        int y = maxHeap.peek();
                        if (x <= y) {
                            maxHeap.remove();
                            minHeap.add(y);
                            maxHeap.add(x);
                        } else {
                            minHeap.add(x);
                        }
                        System.out.print((maxHeap.peek() + minHeap.peek())/2 + " ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
