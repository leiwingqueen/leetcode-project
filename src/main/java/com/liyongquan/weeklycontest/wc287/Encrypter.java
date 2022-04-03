package com.liyongquan.weeklycontest.wc287;

import java.util.ArrayList;
import java.util.List;

public class Encrypter {
    private char[] keys;
    private String[] values;
    private TrieTree trieTree;

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        this.keys = keys;
        this.values = values;
        trieTree = new TrieTree();
        for (String s : dictionary) {
            trieTree.add(s);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word1.length(); i++) {
            char ch = word1.charAt(i);
            int j = 0;
            while (j < keys.length && keys[j] != ch) {
                j++;
            }
            sb.append(values[j]);
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return backtrace(0, word2, trieTree.root);
    }

    private int backtrace(int idx, String word, TrieNode trieNode) {
        int n = word.length() / 2;
        if (idx == n) {
            if (trieNode.end) {
                return 1;
            } else {
                return 0;
            }
        }
        String sub = word.substring(2 * idx, 2 * idx + 2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i].compareTo(sub) == 0) {
                list.add(i);
            }
        }
        int cnt = 0;
        for (Integer i : list) {
            if (trieNode.child[keys[i] - 'a'] != null) {
                cnt += backtrace(idx + 1, word, trieNode.child[keys[i] - 'a']);
            }
        }
        return cnt;
    }

    static class TrieTree {
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
