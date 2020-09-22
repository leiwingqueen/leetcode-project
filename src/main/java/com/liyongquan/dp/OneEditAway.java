package com.liyongquan.dp;

import java.awt.*;
import java.util.Map;
import java.util.Queue;

/**
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/one-away-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OneEditAway {
    /**
     * 贪心？
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        boolean change = false;
        int i = 0, j = 0;
        while (i < first.length()) {
            if (j < second.length()) {
                if (first.charAt(i) == second.charAt(j)) {
                    i++;
                    j++;
                    continue;
                }
                if (change) {
                    return false;
                }
                //3种场景
                if (first.length() == second.length()) {
                    return eq(first, second, i + 1, j + 1);
                } else if (first.length() < second.length()) {
                    return eq(first, second, i, j + 1);
                } else {
                    return eq(first, second, i + 1, j);
                }
            } else {
                if (change) {
                    return false;
                }
                //这里相当于删除操作
                change = true;
                i++;
            }
        }
        return true;
    }

    private boolean eq(String s1, String s2, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) {
            return true;
        }
        if (i >= s1.length()) {
            return false;
        }
        if (j >= s2.length()) {
            return false;
        }
        return s1.substring(i).compareTo(s2.substring(j)) == 0;
    }
}
