package com.liyongquan.array;

/**
 * 243. 最短单词距离
 * <p>
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * <p>
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 * <p>
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-word-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ShortestDistance {
    /**
     * 暴力解法
     *
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int len = wordsDict.length;
        int res = len;
        for (int i = 0; i < len; i++) {
            if (wordsDict[i].compareTo(word1) == 0) {
                //从两边扩散
                int j = 1;
                while (i - j >= 0 || i + j < len) {
                    if (i - j >= 0 && wordsDict[i - j].compareTo(word2) == 0 ||
                            i + j < len && wordsDict[i + j].compareTo(word2) == 0) {
                        res = Math.min(res, j);
                        break;
                    }
                    j++;
                }
            }
        }
        return res;
    }

    /**
     * 增加两个变量记录最近出现的位置
     *
     * @param wordsDict
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance2(String[] wordsDict, String word1, String word2) {
        int len = wordsDict.length;
        int res = len;
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < len; i++) {
            if (wordsDict[i].compareTo(word1) == 0) {
                idx1 = i;
            } else if (wordsDict[i].compareTo(word2) == 0) {
                idx2 = i;
            }
            if (idx1 >= 0 && idx2 >= 0) {
                res = Math.min(res, Math.abs(idx1 - idx2));
            }
        }
        return res;
    }
}
