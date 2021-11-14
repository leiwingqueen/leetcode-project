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
        tree.add(key, val);
    }

    public int sum(String prefix) {
        TrieNode node = tree.scan(prefix);
        return node == null ? 0 : node.val;
    }

    public class TrieTree {
        private TrieNode root;

        public TrieTree() {
            root = new TrieNode('-');
        }

        public TrieNode add(String word, int val) {
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
            }
            cur.val = val;
            return cur;
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

        public int sum(String prefix) {
            TrieNode cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (cur.child[ch - 'a'] == null) {
                    return 0;
                }
                cur = cur.child[ch - 'a'];
            }
            //统计下面的所有节点
            return dfs(cur);
        }

        private int dfs(TrieNode node) {
            if (node == null) {
                return 0;
            }
            int sum = node.val;
            for (TrieNode child : node.child) {
                sum += dfs(child);
            }
            return sum;
        }
    }

    static class TrieNode {
        char ch;
        boolean end;
        private TrieNode[] child = new TrieNode[26];
        private int val;

        public TrieNode(char ch) {
            this.ch = ch;
            this.end = false;
            this.val = 0;
        }
    }
}
