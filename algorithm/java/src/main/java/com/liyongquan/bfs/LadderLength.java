package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Target;
import java.util.*;

/**
 * 127. 单词接龙
 * <p>
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class LadderLength {

    private static class Pair {
        int key;
        int value;
        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 构造一个无向图，然后用bfs寻找最短路径
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        map.put(beginWord, idx++);
        for (String s : wordList) {
            map.put(s, idx++);
        }
        if (!map.containsKey(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        int len = wordList.size();
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < len; i++) {
            boolean con = check(beginWord, wordList.get(i));
            if (con) {
                if (!edges.containsKey(0)) {
                    edges.put(0, new LinkedList<>());
                }
                if (!edges.containsKey(i + 1)) {
                    edges.put(i + 1, new LinkedList<>());
                }
                edges.get(0).add(i + 1);
                edges.get(i + 1).add(0);
            }
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                boolean con = check(wordList.get(i), wordList.get(j));
                if (con) {
                    if (!edges.containsKey(i + 1)) {
                        edges.put(i + 1, new LinkedList<>());
                    }
                    if (!edges.containsKey(j + 1)) {
                        edges.put(j + 1, new LinkedList<>());
                    }
                    edges.get(i + 1).add(j + 1);
                    edges.get(j + 1).add(i + 1);
                }
            }
        }
        Integer start = 0;
        Integer end = map.get(endWord);
        //log.info("end:{}", end);
        //bfs找到最短路径
        Queue<Pair> queue = new LinkedList();
        int[] visit = new int[len + 1];
        visit[start] = 1;
        queue.add(new Pair(start, 1));
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            Integer index = poll.key;
            Integer depth = poll.value;
            if (edges.containsKey(index)) {
                for (Integer next : edges.get(index)) {
                    if (visit[next] == 0) {
                        if (next.intValue() == end) {
                            return depth + 1;
                        }
                        visit[next] = 1;
                        queue.add(new Pair(next, depth + 1));
                        //log.info("add node:{}", next);
                    }
                }
            }
        }
        return 0;
    }

    private boolean check(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
            }
        }
        return cnt == 1;
    }
}
