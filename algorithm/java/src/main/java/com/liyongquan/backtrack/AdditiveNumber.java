package com.liyongquan.backtrack;

//306. 累加数
//累加数 是一个字符串，组成它的数字可以形成累加序列。
//
// 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
//
// 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
//
// 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
//
//
//
// 示例 1：
//
//
//输入："112358"
//输出：true
//解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
//
//
// 示例 2：
//
//
//输入："199100199"
//输出：true
//解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
//
//
//
// 提示：
//
//
// 1 <= num.length <= 35
// num 仅由数字（0 - 9）组成
//
//
//
//
// 进阶：你计划如何处理由过大的整数输入导致的溢出?
// Related Topics 字符串 回溯 👍 274 👎 0

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        for (int i = 1; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                if (check(num, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(String num, int p1, int p2) {
        //不能以0开头
        if (p1 > 1 && num.charAt(0) == '0') {
            return false;
        }
        if (p2 - p1 > 1 && num.charAt(p1) == '0') {
            return false;
        }
        BigDecimal n1 = new BigDecimal(num.substring(0, p1));
        BigDecimal n2 = new BigDecimal(num.substring(p1, p2));
        int p = p2;
        while (p < num.length()) {
            BigDecimal n3 = n1.add(n2);
            List<Integer> list = number2list(n3);
            if (p + list.size() > num.length()) {
                return false;
            }
            for (Integer item : list) {
                if (item.intValue() != (num.charAt(p++) - '0')) {
                    return false;
                }
            }
            n1 = n2;
            n2 = n3;
        }
        return true;
    }

    private List<Integer> number2list(BigDecimal num) {
        LinkedList<Integer> list = new LinkedList<>();
        while (num.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal[] divide = num.divideAndRemainder(BigDecimal.TEN);
            list.offerFirst(divide[1].intValue());
            num = divide[0];
        }
        if (list.size() == 0) {
            list.offerFirst(0);
        }
        return list;
    }
}
