package com.liyongquan.design;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Trie {
    public class TrieNode {
        private Character value;
        private TrieNode[] child;
        private boolean exist;

        public TrieNode(Character value, boolean exist) {
            this.value = value;
            this.exist = exist;
            child = new TrieNode[26];
        }

        public Character getValue() {
            return value;
        }

        public void setValue(Character value) {
            this.value = value;
        }

        public TrieNode[] getChild() {
            return child;
        }

        public void setChild(TrieNode[] child) {
            this.child = child;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode('-', false);
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word.length() == 0) {
            return;
        }
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            boolean exist = i == word.length() - 1;
            char c = word.charAt(i);
            int idx = c - 'a';
            if (cur.getChild()[idx] == null) {
                cur.getChild()[idx] = new TrieNode(c, exist);
            } else {
                if (exist) {
                    cur.getChild()[idx].setExist(true);
                }
            }
            //移动到下一个节点
            cur = cur.getChild()[idx];
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = scan(word);
        return node != null && node.isExist();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = scan(prefix);
        return node != null;
    }

    private TrieNode scan(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (cur.getChild()[idx] == null) {
                return null;
            }
            cur = cur.getChild()[idx];
        }
        return cur;
    }
}
