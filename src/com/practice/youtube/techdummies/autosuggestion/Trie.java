package com.practice.youtube.techdummies.autosuggestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Trie {

    // Default rating value.
    private static final int RATING_NOT_AVAILABLE = -1;

    // Character assigned to the trie root.
    private static final char ROOT_DATA = '$';
    private static final String EMPTY_STRING = "";

    // TreeSet to hold each entry of < rating and word > with the current prefix. (Used during pre-compute step.)
    private TreeSet<Entry> treeSet;

    // Children nodes
    private Map<Character, Trie> children;

    // Default rating to all the node.
    private int rating = RATING_NOT_AVAILABLE;

    // Each node data.
    private Character data;

    public Trie(final Character data) {
        this.treeSet = new TreeSet<>((o1, o2) -> o2.rating.compareTo(o1.rating));
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

    public void addWordWithRatingAndDoPreCompute(final String word, final int rating) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            node.children.putIfAbsent(ch, new Trie(ch));
            node = node.children.get(ch);
            node.treeSet.add(new Entry(rating, word));
        }
        node.rating = rating;
    }

    public void prePopulate() {
        preComputeDfsOnTrie(this, EMPTY_STRING);
    }

    private void preComputeDfsOnTrie(final Trie node, final String prefix) {
        if (node.rating != -1) {
            node.treeSet.add(new Entry(node.rating, prefix));
        }

        for (Map.Entry<Character, Trie> entry : node.children.entrySet()) {
            preComputeDfsOnTrie(entry.getValue(), prefix + entry.getKey());
            node.treeSet.addAll(entry.getValue().treeSet);
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

        for (Entry entry : node.treeSet) {
            words.add(entry.word);
        }
        return words;
    }

    public List<String> wordsWithGivenPrefixWithoutPreCompute(final String prefix) {
        Trie node = this;
        final TreeSet<Entry> words = new TreeSet<>((o1, o2) -> o2.rating.compareTo(o1.rating));
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            node = node.children.getOrDefault(ch, null);
            if (Objects.isNull(node)) {
                return Collections.emptyList();
            }
        }

        dfsOnTrie(words, prefix, node);
        return words.stream()
                .map(e -> e.word)
                .collect(Collectors.toList());
    }

    private void dfsOnTrie(final TreeSet<Entry> words, final String prefix, final Trie node) {
        if (node.rating != -1) {
            words.add(new Entry(node.rating, prefix));
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

    private static class Entry {

        private Integer rating;
        private String word;

        public Entry(final Integer rating, final String word) {
            this.rating = rating;
            this.word = word;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Entry entry = (Entry)o;
            return Objects.equals(rating, entry.rating) &&
                    Objects.equals(word, entry.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rating, word);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Entry{");
            sb.append("rating=").append(rating);
            sb.append(", word='").append(word).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
