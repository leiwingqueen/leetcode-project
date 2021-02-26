package com.liyongquan.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1178. 猜字谜
 * <p>
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * <p>
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * <p>
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindNumOfValidWords {
    /**
     * 先尝试暴力解法，不过这个数量级一定会超时的
     * <p>
     * 假设words的长度为n,puzzles的长度为m，每个单词的长度为k
     * 时间复杂度:
     * 初始化map:O(n*k+m*k)
     * 遍历:
     * O(m*n*k)
     * <p>
     * 所以总的时间复杂度为O(m*n*k)
     *
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int wordLen = words.length, puzzleLen = puzzles.length;
        List<Integer> res = new ArrayList<>(puzzleLen);
        //空间换时间，初始化word和puzzle的bitmap
        int[][] wordBitMap = new int[wordLen][26], puzzleBitMap = new int[puzzleLen][26];
        for (int i = 0; i < wordLen; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                wordBitMap[i][words[i].charAt(j) - 'a']++;
            }
        }
        for (int i = 0; i < puzzleLen; i++) {
            for (int j = 0; j < puzzles[i].length(); j++) {
                puzzleBitMap[i][puzzles[i].charAt(j) - 'a']++;
            }
        }
        //遍历
        for (int i = 0; i < puzzleLen; i++) {
            int cnt = 0;
            for (int j = 0; j < wordLen; j++) {
                if (match(puzzles[i], words[j], puzzleBitMap[i], wordBitMap[j])) {
                    cnt++;
                }
            }
            res.add(cnt);
        }
        return res;
    }

    private boolean match(String puzzle, String word, int[] puzzleMap, int[] wordMap) {
        //包含第一个字符
        if (wordMap[puzzle.charAt(0) - 'a'] == 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (puzzleMap[word.charAt(i) - 'a'] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 状态压缩
     *
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        int wordLen = words.length, puzzleLen = puzzles.length;
        List<Integer> res = new ArrayList<>(puzzleLen);
        //空间换时间，初始化word和puzzle的bitmap
        Map<Integer, Integer> freq = new HashMap<>();
        for (String word : words) {
            int bit = str2bit(word);
            freq.put(bit, freq.getOrDefault(bit, 0) + 1);
        }

        for (String puzzle : puzzles) {
            List<Integer> subset = subset(puzzle);
            int cnt = 0;
            for (Integer bit : subset) {
                cnt += freq.getOrDefault(bit, 0);
            }
            res.add(cnt);
        }
        return res;
    }

    private int str2bit(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res |= (1 << str.charAt(i) - 'a');
        }
        return res;
    }

    private List<Integer> subset(String str) {
        int len = str.length();
        //必须包含第一个字符
        List<Integer> res = new ArrayList<>();
        //从第二个位点开始扫描(排除第一个字符)
        subset(1 << str.charAt(0) - 'a', str, 1, res);
        return res;
    }

    private void subset(int path, String str, int idx, List<Integer> res) {
        int len = str.length();
        if (idx >= len) {
            res.add(path);
            return;
        }
        subset(path, str, idx + 1, res);
        subset(path | 1 << (str.charAt(idx) - 'a'), str, idx + 1, res);
    }
}
