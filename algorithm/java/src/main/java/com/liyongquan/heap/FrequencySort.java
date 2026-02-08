package com.liyongquan.heap;

import com.liyongquan.bit.HammingDistance;

import java.util.*;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "tree"
 * <p>
 * 输出:
 * "eert"
 * <p>
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * <p>
 * 输入:
 * "cccaaa"
 * <p>
 * 输出:
 * "cccaaa"
 * <p>
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * <p>
 * 输入:
 * "Aabb"
 * <p>
 * 输出:
 * "bbAa"
 * <p>
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FrequencySort {

    private static class Pair {
        char key;
        int value;
        Pair(char key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 朴素解法
     * <p>
     * 时间复杂度O(nlogn)
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        //统计出现次数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        List<Pair> list = new ArrayList<>(map.size());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            list.add(new Pair(entry.getKey(), entry.getValue()));
        }
        //排序
        Collections.sort(list, (p1, p2) -> p2.value - p1.value);
        //输出
        StringBuilder sb = new StringBuilder(s.length());
        for (Pair pair : list) {
            for (int i = 0; i < pair.value; i++) {
                sb.append(pair.key);
            }
        }
        return sb.toString();
    }

    /**
     * 堆排
     *
     * @param s
     * @return
     */
    public String frequencySort2(String s) {
        if (s.length() == 0) {
            return s;
        }
        //统计出现次数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(map.size(), (p1, p2) -> p2.value - p1.value);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }
        //输出
        StringBuilder sb = new StringBuilder(s.length());
        while (pq.size() > 0) {
            Pair pair = pq.poll();
            for (int i = 0; i < pair.value; i++) {
                sb.append(pair.key);
            }
        }
        return sb.toString();
    }
}
