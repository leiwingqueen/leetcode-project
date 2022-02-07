package com.liyongquan.greedy;

import javafx.util.Pair;

import java.sql.Statement;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

//如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
//
//给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
//
//s 是一个尽可能长的快乐字符串。
//s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
//s 中只含有 'a'、'b' 、'c' 三种字母。
//如果不存在这样的字符串 s ，请返回一个空字符串 ""。
//
// 
//
//示例 1：
//
//输入：a = 1, b = 1, c = 7
//输出："ccaccbcc"
//解释："ccbccacc" 也是一种正确答案。
//示例 2：
//
//输入：a = 2, b = 2, c = 1
//输出："aabbc"
//示例 3：
//
//输入：a = 7, b = 1, c = 0
//输出："aabaa"
//解释：这是该测试用例的唯一正确答案。
// 
//
//提示：
//
//0 <= a, b, c <= 100
//a + b + c > 0
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-happy-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LongestDiverseString {
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        if (a > 0) {
            pq.add(new Pair<>('a', a));
        }
        if (b > 0) {
            pq.add(new Pair<>('b', b));
        }
        if (c > 0) {
            pq.add(new Pair<>('c', c));
        }
        StringBuilder sb = new StringBuilder();
        char last = ' ';
        int cnt = 0;
        while (pq.size() > 0) {
            Pair<Character, Integer> pair = pq.poll();
            if (pair.getKey() != last) {
                last = pair.getKey();
                cnt = 1;
                sb.append(pair.getKey());
                if (pair.getValue() > 1) {
                    pq.offer(new Pair<>(pair.getKey(), pair.getValue() - 1));
                }
            } else {
                if (cnt < 2) {
                    cnt++;
                    sb.append(pair.getKey());
                    if (pair.getValue() > 1) {
                        pq.offer(new Pair<>(pair.getKey(), pair.getValue() - 1));
                    }
                } else {
                    if (pq.isEmpty()) {
                        return sb.toString();
                    }
                    Pair<Character, Integer> next = pq.poll();
                    sb.append(next.getKey());
                    last = next.getKey();
                    cnt = 1;
                    if (next.getValue() > 1) {
                        pq.offer(new Pair<>(next.getKey(), next.getValue() - 1));
                    }
                    pq.offer(pair);
                }
            }
        }
        return sb.toString();
    }
}
