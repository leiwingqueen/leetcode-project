package com.liyongquan.design;

public class Trie2 {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie2() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }
            node = node.child[idx];
        }
        node.end = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.child[idx] == null) {
                return false;
            }
            node = node.child[idx];
        }
        return node.end;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (node.child[idx] == null) {
                return false;
            }
            node = node.child[idx];
        }
        return true;
    }

    private class TrieNode {
        TrieNode[] child;
        boolean end;

        public TrieNode() {
            this(false);
        }

        public TrieNode(boolean end) {
            this.child = new TrieNode[26];
            this.end = end;
        }
    }
}
