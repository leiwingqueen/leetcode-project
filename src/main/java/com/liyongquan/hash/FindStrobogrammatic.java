package com.liyongquan.hash;

//中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
//
// 找到所有长度为 n 的中心对称数。
//
// 示例 :
//
// 输入:  n = 2
//输出: ["11","69","88","96"]
//
// Related Topics 递归 数学
// 👍 59 👎 0

import java.util.*;
import java.util.stream.Collectors;

public class FindStrobogrammatic {
    private static Map<Character, Character> revert = new HashMap<>();

    static {
        revert.put('1', '1');
        revert.put('6', '9');
        revert.put('9', '6');
        revert.put('8', '8');
        revert.put('0', '0');
    }

    public List<String> findStrobogrammatic(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<String> list = dfs(n);
        return list.stream().filter(s -> s.length() == 1 || s.charAt(0) != '0').collect(Collectors.toList());
    }

    private List<String> dfs(int n) {
        if (n == 0) {
            return Arrays.asList("");
        }
        if (n == 1) {
            List<String> res = new LinkedList<>();
            for (Map.Entry<Character, Character> entry : revert.entrySet()) {
                if (entry.getValue() == entry.getKey()) {
                    res.add(String.valueOf(entry.getKey()));
                }
            }
            return res;
        }
        List<String> res = new LinkedList<>();
        List<String> list = dfs(n - 2);
        for (Map.Entry<Character, Character> entry : revert.entrySet()) {
            for (String s : list) {
                String ns = entry.getKey() + s + entry.getValue();
                res.add(ns);
            }
        }
        return res;
    }
}
