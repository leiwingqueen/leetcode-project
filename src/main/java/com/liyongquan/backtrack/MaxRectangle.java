package com.liyongquan.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 17.25. 单词矩阵
 * <p>
 * 给定一份单词的清单，设计一个算法，创建由字母组成的面积最大的矩形，其中每一行组成一个单词(自左向右)，每一列也组成一个单词(自上而下)。不要求这些单词在清单里连续出现，但要求所有行等长，所有列等高。
 * <p>
 * 如果有多个面积最大的矩形，输出任意一个均可。一个单词可以重复使用。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["this", "real", "hard", "trh", "hea", "iar", "sld"]
 * 输出:
 * [
 *    "this",
 *    "real",
 *    "hard"
 * ]
 * 示例 2:
 * <p>
 * 输入: ["aa"]
 * 输出: ["aa","aa"]
 * 说明：
 * <p>
 * words.length <= 1000
 * words[i].length <= 100
 * 数据保证单词足够随机
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-rectangle-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxRectangle {
    public String[] maxRectangle(String[] words) {
        //分组
        Map<Integer, List<String>> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word.length())) {
                map.put(word.length(), new ArrayList<>());
            }
            map.get(word.length()).add(word);
        }
        String[][] group = new String[map.size()][];
        int idx = 0;
        for (List<String> list : map.values()) {
            group[idx] = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                group[idx][i] = list.get(i);
            }
            idx++;
        }
        //选择两个分组进行组合尝试
        int max = 0;
        String[] maxRes = {};
        for (int i = 0; i < group.length; i++) {
            for (int j = 0; j < group.length; j++) {
                int row = group[j].length, col = group[i].length;
                String[] res = new String[row];
                if (match(group[i], new TrieTree(), row, col, new String[row], 0, res) && max < row * col) {
                    max = row * col;
                    maxRes = res;
                }
            }
        }
        return maxRes;
    }

    private boolean match(String[] group1, TrieTree tree, int row, int col, String[] path, int idx, String[] res) {
        if (idx == row) {
            for (int i = 0; i < row; i++) {
                res[i] = path[i];
            }
            return true;
        }
        for (int i = 0; i < row; i++) {
            path[idx++] = group1[i];
            boolean flag = true;
            for (int j = 0; j < col; j++) {
                char[] str = new char[idx];
                for (int k = 0; k < idx; k++) {
                    str[k] = path[k].charAt(j);
                }
                if (!tree.contains(str)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            if (match(group1, tree, row, col, path, idx, res)) {
                return true;
            }
            idx--;
        }
        return false;
    }

    private static class TrieTree {
        public boolean contains(char[] str) {
            return true;
        }
    }
}
