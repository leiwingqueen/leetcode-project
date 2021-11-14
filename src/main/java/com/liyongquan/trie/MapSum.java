package com.liyongquan.trie;

/**
 * @author liyongquan
 * @date 2021/11/14
 */
public class MapSum {
    private TrieTree tree;

    public MapSum() {
        tree = new TrieTree();
    }

    public void insert(String key, int val) {
        boolean add = tree.add(key);
        if (add) {
            tree.updateCnt(key);
        }
    }

    public int sum(String prefix) {
        TrieNode node = tree.scan(prefix);
        return node == null ? 0 : node.cnt;
    }

    public class TrieTree {
        private TrieNode root;

        public TrieTree() {
            root = new TrieNode('-');
        }

        public boolean add(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int idx = c - 'a';
                if (cur.child[idx] == null) {
                    cur.child[idx] = new TrieNode(c);
                }
                cur = cur.child[idx];
            }
            if (!cur.end) {
                cur.end = true;
                return true;
            } else {
                return false;
            }
        }

        public boolean containPrefix(String prefix) {
            TrieNode node = scan(prefix);
            return node != null;
        }

        public boolean contain(String word) {
            TrieNode node = scan(word);
            return node != null && node.end;
        }

        public TrieNode scan(String word) {
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

        public void updateCnt(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int idx = c - 'a';
                cur.child[idx].cnt++;
                cur = cur.child[idx];
            }
        }
    }

    static class TrieNode {
        char ch;
        boolean end;
        private TrieNode[] child = new TrieNode[26];
        private int cnt;

        public TrieNode(char ch) {
            this.ch = ch;
            this.end = false;
        }
    }
}
