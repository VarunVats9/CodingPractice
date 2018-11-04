package com.practice.youtube.techdummies.lru;


public class DoublyLinkedList {

    private int size = 0;

    private Node last, first;

    public void addLast(Node node) {
        if (first == null) {
            first = node;
            last = first;
            size++;
            return;
        }

        size++;
        last.next = node;
        node.prev = last;
        last = node;
    }

    public void removeNodeAndAddToLast(Node node) {

        if (last == node) {
            return;
        }

        if (first == node) {
            Node temp = first;
            first = first.next;
            temp.next = null;
            addLast(temp);
            return;
        }

        Node previous = node.prev;
        Node forward = node.next;

        previous.next = forward;
        forward.prev = previous;
        node.next = null;
        node.prev = null;

        addLast(node);
    }

    public Node removeFirst() {
        if (first == null) {
            return null;
        }
        size--;
        Node temp = first;
        first = first.next;
        temp.next = null;
        return temp;
    }

    public int size() {
        return this.size;
    }

    public String print() {
        Node temp = first;
        StringBuilder stringBuilder = new StringBuilder("[");
        while (temp != null) {
            stringBuilder.append(temp.power.getSuperHeroName() + ",");
            temp = temp.next;
        }
        stringBuilder.deleteCharAt((stringBuilder.length() - 1));
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }

    public static class Node {

        private Node next, prev;
        private LRU.Power power;

        public Node(final LRU.Power power) {
            this.power = power;
        }

        public LRU.Power getPower() {
            return this.power;
        }
    }
}
