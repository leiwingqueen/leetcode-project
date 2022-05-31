package com.liyongquan.string;

//现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
//
//给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
//
//请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
//
//字符串 s 字典顺序小于 字符串 t 有两种情况：
//
//在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
//如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
// 
//
//示例 1：
//
//输入：words = ["wrt","wrf","er","ett","rftt"]
//输出："wertf"
//示例 2：
//
//输入：words = ["z","x"]
//输出："zx"
//示例 3：
//
//输入：words = ["z","x","z"]
//输出：""
//解释：不存在合法字母顺序，因此返回 "" 。
// 
//
//提示：
//
//1 <= words.length <= 100
//1 <= words[i].length <= 100
//words[i] 仅由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/Jf1JuT
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import com.liyongquan.math.TrailingZeroes;

import java.util.LinkedList;
import java.util.Queue;

public class AlienOrder {
    /**
     * 假设A>B，那么我们认为A->B存在一条通路，那么我们需要做的就是判断图是否存在环？
     *
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        int size = 26;
        int[][] graph = new int[size][size];
        //统计每个点的入度
        int[] degree = new int[size];
        boolean[] exist = new boolean[size];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                exist[word.charAt(i) - 'a'] = true;
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String w1 = words[i];
                String w2 = words[j];
                int p = 0;
                int len = Math.min(w1.length(), w2.length());
                while (p < len && w1.charAt(p) == w2.charAt(p)) {
                    p++;
                }
                if (p == len) {
                    //不合法关系
                    if (w1.length() > w2.length()) {
                        return "";
                    }
                } else {
                    //能得到对应的关系
                    //w1[p]<w2[p]
                    if (check(graph, w2.charAt(p) - 'a', w1.charAt(p) - 'a')) {
                        //证明存在环
                        return "";
                    }
                    graph[w1.charAt(p) - 'a'][w2.charAt(p) - 'a'] = 1;
                    degree[w2.charAt(p) - 'a']++;
                }
            }
        }
        //找到入度是0的点作为起始节点开始扫描，拓补排序
        //TODO:拓补排序
        return "";
    }

    /**
     * 判断从start->end是否存在一条通路
     *
     * @param graph
     * @param start
     * @param end
     * @return
     */
    private boolean check(int[][] graph, int start, int end) {
        if (start == end) {
            return true;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[graph.length];
        queue.offer(start);
        visit[start] = true;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll.intValue() == end) {
                return true;
            }
            for (int i = 0; i < graph.length; i++) {
                if (graph[poll][i] == 1 && !visit[i]) {
                    visit[i] = true;
                    queue.add(i);
                }
            }
        }
        return false;
    }
}
