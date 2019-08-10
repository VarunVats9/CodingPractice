package com.practice.codingblocks.practice.heap;

import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeap implements Comparator<Long> {

    private ArrayList<Long> heap = new ArrayList<>();

    @Override
    public int compare(Long o1, Long o2) {
        return o2.compareTo(o1);
    }

    public void add(long elem) {
        heap.add(elem);
        maxHeapifyDownTop();
    }

    private void maxHeapifyDownTop() {
        int idx = size() - 1;
        while (idx > 0 && heap.get(idx) > heap.get(getParent(idx))) {
            int pIdx = getParent(idx);
            swap(idx, pIdx);
            idx = pIdx;
        }
    }

    private void swap(final int idx, final int pIdx) {
        long temp = heap.get(pIdx);
        heap.set(pIdx, heap.get(idx));
        heap.set(idx, temp);
    }

    public int size() {
        return this.heap.size();
    }

    public long peek() {
        return heap.get(0);
    }

    public void remove() {
        swapWithEnd();
        maxHeapifyTopDown();
    }

    private void maxHeapifyTopDown() {
        int idx = 0;
        while (true) {
            int lC = getLeftChild(idx);
            int rC = getRightChild(idx);
            if (lC >= size()) {
                break;
            }
            long lV = heap.get(lC);
            if (rC >= size()) {
                if (lV > heap.get(idx)) {
                    swap(lC, idx);
                    idx = lC;
                } else {
                    break;
                }
            } else {
                long rV = heap.get(rC);
                if (heap.get(idx) >= lV && heap.get(idx) >= rV) {
                    break;
                }
                if (lV > rV) {
                    swap(lC, idx);
                    idx = lC;
                } else {
                    swap(rC, idx);
                    idx = rC;
                }
            }
        }
    }

    private void swapWithEnd() {
        long temp = heap.get(this.size() - 1);
        heap.set(0, temp);
        heap.remove(this.size() - 1);
    }
    
    private int getLeftChild(int idx) {
        return 2*idx + 1;
    }

    private int getRightChild(int idx) {
        return 2*idx + 2;
    }
    
    private int getParent(int idx) {
        return (idx - 1)/2;
    }

}
