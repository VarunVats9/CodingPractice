package com.practice.youtube.channel.techdummies.autosuggestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Trie {

    private static final int RATING_NOT_AVAILABLE = -1;
    private static final char ROOT_DATA = '$';
    private static final String EMPTY_STRING = "";

    private TreeMap<String, Integer> treeMap;
    private Map<Character, Trie> children;
    private int rating = RATING_NOT_AVAILABLE;
    private Character data;

    public Trie(final Character data) {
        this.treeMap = new TreeMap<>();
        this.children = new HashMap<>();
        this.data = data;
    }

    public Trie() {
        this(ROOT_DATA);
    }

    public void addWordWithRating(final String word, final int rating) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            node.children.putIfAbsent(ch, new Trie(ch));
            node = node.children.get(ch);
        }
        node.rating = rating;
    }

    public void prePopulate() {
        preComputeDfsOnTrie(this, EMPTY_STRING);
    }

    private void preComputeDfsOnTrie(final Trie node, final String prefix) {
        if (node.rating != -1) {
            node.treeMap.put(prefix, node.rating);
        }

        for (Map.Entry<Character, Trie> entry : node.children.entrySet()) {
            preComputeDfsOnTrie(entry.getValue(), prefix + entry.getKey());
            node.treeMap.putAll(entry.getValue().treeMap);
        }
    }

    public List<String> wordsWithGivenPrefixWithPreCompute(final String prefix) {
        Trie node = this;
        final List<String> words = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            node = node.children.getOrDefault(ch, null);
            if (Objects.isNull(node)) {
                return Collections.emptyList();
            }
        }

        for (Map.Entry<String, Integer> entry : node.treeMap.entrySet()) {
            words.add(entry.getKey());
        }
        return words;
    }

    public List<String> wordsWithGivenPrefixWithoutPreCompute(final String prefix) {
        Trie node = this;
        final List<String> words = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            node = node.children.getOrDefault(ch, null);
            if (Objects.isNull(node)) {
                return Collections.emptyList();
            }
        }

        dfsOnTrie(words, prefix, node);
        return words;
    }

    private void dfsOnTrie(final List<String> words, final String prefix, final Trie node) {
        if (node.rating != -1) {
            words.add(prefix);
        }
        for (Map.Entry<Character, Trie> entry : node.children.entrySet()) {
            dfsOnTrie(words, prefix + entry.getKey(), entry.getValue());
        }
    }

    private void printTreeMap(final TreeMap<String, Integer> treeMap) {
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.print("[" + entry.getKey() + ":" + entry.getValue() + "], ");
        }
        System.out.println();
    }

}
