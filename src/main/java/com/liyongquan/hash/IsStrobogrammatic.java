package com.liyongquan.hash;

//中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
//
// 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
//
//
//
// 示例 1:
//
// 输入: num = "69"
//输出: true
//
//
// 示例 2:
//
// 输入: num = "88"
//输出: true
//
// 示例 3:
//
// 输入: num = "962"
//输出: false
//
// 示例 4：
//
// 输入：num = "1"
//输出：true
//
// Related Topics 哈希表 数学
// 👍 27 👎 0

import java.util.HashMap;
import java.util.Map;

public class IsStrobogrammatic {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> revert = new HashMap<>();
        revert.put('1', '1');
        revert.put('6', '9');
        revert.put('9', '6');
        revert.put('8', '8');
        revert.put('0', '0');
        //两个指针
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            char left = num.charAt(l++);
            char right = num.charAt(r--);
            if (!revert.containsKey(left) || !revert.containsKey(right) || revert.get(left) != right) {
                return false;
            }
        }
        return true;
    }
}
