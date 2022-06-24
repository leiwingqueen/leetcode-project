package com.liyongquan.trie;

/**
 * @author liyongquan
 * @date 2021/11/14
 */
public class TrieTree {
    protected TrieNode root;

    public TrieTree() {
        root = new TrieNode('-');
    }

    public void add(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (cur.child[idx] == null) {
                cur.child[idx] = new TrieNode(c);
            }
            cur = cur.child[idx];
        }
        cur.end = true;
    }

    public boolean containPrefix(String prefix) {
        TrieNode node = scan(prefix);
        return node != null;
    }

    public boolean contain(String word) {
        TrieNode node = scan(word);
        return node != null && node.end;
    }

    private TrieNode scan(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.child[ch - 'a'] == null) {
                return null;
            }
            cur = cur.child[ch - 'a'];
        }
        return cur;
    }

    static class TrieNode {
        char ch;
        boolean end;
        protected TrieNode[] child = new TrieNode[26];

        public TrieNode(char ch) {
            this.ch = ch;
            this.end = false;
        }
    }
}

