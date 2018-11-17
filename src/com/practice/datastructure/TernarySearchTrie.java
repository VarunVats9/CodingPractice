package com.practice.datastructure;

import java.util.Objects;

/*
 * Created by vvats on 16/11/18.
 */
public class TernarySearchTrie<V> {

    private TernaryNode root;

    public void put(final String key, final V value) {
        root = put(root, key, value, 0);
    }

    private TernaryNode put(TernaryNode node, final String key, final V value, final int idx) {
        char c = key.charAt(idx);

        if (Objects.isNull(node)) {
            node = new TernaryNode();
            node.splitChar = c;
        }

        if (c > node.splitChar) node.right = put(node.right, key, value, idx);
        else if (c < node.splitChar) node.left = put(node.left, key, value, idx);
        else if (c == node.splitChar) {
            if (idx == key.length() - 1) {
                node.val = value;
            } else {
                node.mid = put(node.mid, key, value, idx + 1);
            }
        }
        return node;
    }

    public V get(final String key) {
        TernaryNode node = get(root, key, 0);
        if (Objects.isNull(node)) {
            return null;
        }
        return node.val;
    }

    private TernaryNode get(final TernaryNode node, final String key, final int idx) {
        if (Objects.isNull(node)) {
            return null;
        }
        char c = key.charAt(idx);

        if (c > node.splitChar) return get(node.right, key, idx);
        else if (c < node.splitChar) return get(node.left, key, idx);
        else if (c == node.splitChar) {
            if (idx == key.length() - 1) {
                return node;
            }
            return get(node.mid, key, idx+1);
        }
        return node;
    }


    private class TernaryNode {

        private TernaryNode left, mid, right;
        private char splitChar;
        private V val;
    }

    public static void main(String[] args) {

        final TernarySearchTrie<Integer> ternarySearchTree = new TernarySearchTrie<>();

        {
            ternarySearchTree.put("cat", 12);
            ternarySearchTree.put("apple", 15);
            ternarySearchTree.put("car", 14);
            ternarySearchTree.put("carrot", 89);
        }

        {
            System.out.println("Check if the word is there in the tree : " + ternarySearchTree.get("cat"));
        }

        {
            System.out.println("Check if the word is there in the tree : " + ternarySearchTree.get("awesome"));
        }

        {
            System.out.println("Check if the word is there in the tree : " + ternarySearchTree.get("carrot"));
        }

    }

}
