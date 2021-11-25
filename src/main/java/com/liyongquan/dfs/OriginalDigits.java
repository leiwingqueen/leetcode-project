package com.liyongquan.dfs;

//423. 从英文中重建数字
//给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
//
// 
//
//示例 1：
//
//输入：s = "owoztneoer"
//输出："012"
//示例 2：
//
//输入：s = "fviefuro"
//输出："45"
// 
//
//提示：
//
//1 <= s.length <= 105
//s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
//s 保证是一个符合题目要求的字符串
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author liyongquan
 * @date 2021/11/24
 */
public class OriginalDigits {
    public static final String[] NUMBER = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
    };

    //映射成map的结构
    public static final int[][] NUMBER_MAP = new int[10][26];

    static {
        for (int i = 0; i < NUMBER.length; i++) {
            String number = NUMBER[i];
            for (int j = 0; j < number.length(); j++) {
                int idx = number.charAt(j) - 'a';
                NUMBER_MAP[i][idx]++;
            }
        }
    }

    public String originalDigits(String s) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        backtrace(map, 0, new LinkedList<>());
        return res;
    }

    private String res;

    //增加记忆
    private Map<int[], Boolean> cache = new HashMap<>();

    private boolean backtrace(int[] map, int number, Deque<Integer> path) {
        //结束判断
        if (empty(map)) {
            StringBuilder sb = new StringBuilder();
            for (Integer num : path) {
                sb.append(num);
            }
            res = sb.toString();
            return true;
        }
        if (cache.containsKey(map)) {
            return cache.get(map);
        }
        //由于只需要升序返回，我们后面选的数字一定要比前面大
        for (int i = number; i <= 9; i++) {
            if (canUse(map, i)) {
                path.offerLast(i);
                reduce(map, i);
                if (backtrace(map, i, path)) {
                    add(map, i);
                    int[] clone = map.clone();
                    cache.put(clone, true);
                    return true;
                }
                add(map, i);
                path.pollLast();
            }
        }
        cache.put(map.clone(), false);
        return false;
    }

    private boolean canUse(int[] map, int num) {
        int[] cnt = NUMBER_MAP[num];
        for (int i = 0; i < 26; i++) {
            if (map[i] < cnt[i]) {
                return false;
            }
        }
        return true;
    }

    private void reduce(int[] map, int num) {
        int[] cnt = NUMBER_MAP[num];
        for (int i = 0; i < 26; i++) {
            map[i] -= cnt[i];
        }
    }

    private void add(int[] map, int num) {
        int[] cnt = NUMBER_MAP[num];
        for (int i = 0; i < 26; i++) {
            map[i] += cnt[i];
        }
    }

    private boolean empty(int[] map) {
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
