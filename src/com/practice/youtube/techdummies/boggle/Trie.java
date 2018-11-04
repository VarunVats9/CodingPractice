package com.practice.youtube.techdummies.boggle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Trie {

    // Character assigned to the trie root.
    private static final char ROOT_DATA = '$';

    // Children nodes
    private Map<Character, Trie> children;

    // Indicates the end of a word.
    private boolean endOfAWord;

    // Each node data.
    private Character data;

    public Trie(final Character data) {
        this.children = new HashMap<>();
        this.data = data;
    }

    public Trie() {
        this(ROOT_DATA);
    }

    public void addWord(final String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            node.children.putIfAbsent(ch, new Trie(ch));
            node = node.children.get(ch);
        }
        node.endOfAWord = true;
    }

    public boolean doesNotExist(final String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            node = node.children.get(ch);
            if (Objects.isNull(node)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTheEndWord(final String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            node = node.children.get(ch);
            if (Objects.isNull(node)) {
                return false;
            }
        }
        return node.endOfAWord;
    }
}