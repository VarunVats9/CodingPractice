package com.practice.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Date : 5 Nov, 2018
 * Time : 3:24 PM
 *
 * @author : Varun Vats (varunvats32@gmail.com)
 */
public class BinaryMinHeap<T extends Comparable<T>> {

    /**
     * This data structure is used in creating priority queue.
     * Mostly for dijkstra's algorithms, and prim's algorithms.
     */
    private List<T> array = new ArrayList<T>();
    private Map<T, Integer> pos = new HashMap<>();

    public void buildHeap(List<T> array) {
        this.array = array;
        int size = array.size();

        /*
         * Since, the elements, from floor(n/2)+1, floor(n/2)+2 ... n are all leaves.
         * There is no point running heapify on it. Those are one node heap.
         * Hence, running only from floor(n/2), floor(n/2)-1, ... 1.
         */
        for (int i = (int)Math.floor(size/2); i >= 0; i--) {
            percolateDown(i);
        }
    }

    public void decreaseKey(final T oldItem, final T replacedItem) {
        /*
         * Ideally need to check percolateUp or down, but since this is a min heap,
         * cannot miss if this key has become the root. That is why trying for up.
         */
        final int i = pos.remove(oldItem);
        array.set(i, replacedItem);
        // Update pos.
        updatePos(i, replacedItem);

        percolateUp(i);
    }

    private void updatePos(final int i, final T replacedItem) {
        pos.putIfAbsent(replacedItem, i);
    }

    public void removeKey(final T oldItem, final T replacedWithMinimumKey) {
        decreaseKey(oldItem, replacedWithMinimumKey);
        extractMin();
    }

    public void addAll(final Collection<T> items) {
        for (T item : items) {
            insert(item);
        }
    }

    public void insert(final T item) {
        array.add(item);
        // Update pos.
        updatePos(array.size() - 1, item);

        percolateUp(array.size() - 1);
    }

    public T extractMin() {
        swap(0, array.size() - 1);
        final T min = array.remove(array.size() - 1);
        percolateDown(0);
        return min;
    }

    public void printHeap() {
        System.out.println(array);
    }

    private void percolateUp(final int i) {
        if (i == 0) {
            return;
        }

        if (array.get(parent(i)).compareTo(array.get(i)) > 0) {
            swap(parent(i), i);
            percolateUp(parent(i));
        }
    }

    private int parent(final int i) {
        return (int)(Math.floor(i/2) - (i % 2 == 0 ? 1 : 0));
    }


    private void percolateDown(final int i) {
        int size = array.size();
        int smallest = i;

        if (left(i) < size && array.get(left(i)).compareTo(array.get(smallest)) < 0) {
            smallest = left(i);
        }

        if (right(i) < size && array.get(right(i)).compareTo(array.get(smallest)) < 0) {
            smallest = right(i);
        }

        if (smallest != i) {
            swap(i, smallest);
            percolateDown(smallest);
        }
    }

    private void swap(final int a, final int b) {
        T temp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, temp);
        // Update pos.
        updatePos(pos.get(array.get(b)), array.get(a));
        updatePos(pos.get(array.get(a)), array.get(b));
    }

    private int right(final int i) {
        return 2*i + 2;
    }

    private int left(final int i) {
        return 2*i + 1;
    }


    public static void main(String[] args) {

        BinaryMinHeap<Integer> binaryMinHeap = new BinaryMinHeap<>();

        binaryMinHeap.insert(1);
        binaryMinHeap.printHeap();
        binaryMinHeap.insert(3);
        binaryMinHeap.printHeap();
        binaryMinHeap.insert(2);
        binaryMinHeap.printHeap();

        binaryMinHeap.insert(4);
        binaryMinHeap.printHeap();
        System.out.println("Extract: " + binaryMinHeap.extractMin());
        binaryMinHeap.printHeap();


        binaryMinHeap.insert(7);
        binaryMinHeap.printHeap();
        System.out.println("Extract: " + binaryMinHeap.extractMin());
        System.out.println("Extract: " + binaryMinHeap.extractMin());
        binaryMinHeap.printHeap();

        binaryMinHeap.insert(9);
        binaryMinHeap.insert(6);
        binaryMinHeap.printHeap();
        System.out.println("Extract: " + binaryMinHeap.extractMin());
        binaryMinHeap.printHeap();

        binaryMinHeap.decreaseKey(9, 1);
        binaryMinHeap.printHeap();
        binaryMinHeap.removeKey(1, Integer.MIN_VALUE);
        binaryMinHeap.printHeap();
        System.out.println("Extract: " + binaryMinHeap.extractMin());
        binaryMinHeap.printHeap();
    }
}
