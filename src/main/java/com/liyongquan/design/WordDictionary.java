package com.liyongquan.design;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最调用多 50000 次 addWord 和 search
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordDictionary {
    /**
     * 尝试用前缀树来解决
     */
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode('-', false);
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode next = cur.addChild(ch, i == word.length() - 1);
            cur = next;
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return dfs(root, word);
    }

    private boolean dfs(TrieNode node, String word) {
        if (word.length() == 0 || node == null) {
            return false;
        }
        char c = word.charAt(0);
        boolean end = word.length() == 1;
        if (c == '.') {
            for (TrieNode trieNode : node.child) {
                if (trieNode != null) {
                    if (end) {
                        if (trieNode.end) {
                            return true;
                        }
                    } else {
                        if (dfs(trieNode, word.substring(1))) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            if (node.child[c - 'a'] == null) {
                return false;
            }
            if (end) {
                return node.child[c - 'a'].end;
            } else {
                return dfs(node.child[c - 'a'], word.substring(1));
            }
        }
    }

    public static final int CHAR_SIZE = 26;

    private static class TrieNode {
        char ch;
        TrieNode[] child;
        boolean end;

        public TrieNode(char ch, boolean end) {
            this.ch = ch;
            this.end = end;
            child = new TrieNode[CHAR_SIZE];
        }

        public TrieNode addChild(char ch, boolean end) {
            int idx = ch - 'a';
            if (child[idx] == null) {
                child[idx] = new TrieNode(ch, end);
            } else {
                if (end && !child[idx].end) {
                    child[idx].end = true;
                }
            }
            return child[idx];
        }
    }
}
