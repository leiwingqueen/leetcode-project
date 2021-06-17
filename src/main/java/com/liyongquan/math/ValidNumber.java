package com.liyongquan.math;

/**
 * 65. 有效数字
 * <p>
 * 有效数字（按顺序）可以分成以下几个部分：
 * <p>
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 * <p>
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 * <p>
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "0"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：s = ".1"
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidNumber {
    private int[][] fsm = {
            {1, 2, 5, -1},
            {-1, 2, 5, -1},
            {-1, 2, 3, -1},
            {-1, 4, -1, -1},
            {-1, 4, -1, -1},
            {-1, 4, -1, -1},
    };

    /**
     * 状态机
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        int eCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                eCnt++;
            }
        }
        if (eCnt > 1) {
            return false;
        }
        if (eCnt == 1) {
            String[] split = s.split("e|E");
            if (split.length != 2) {
                return false;
            }
            int s1 = getType(split[0]);
            int s2 = getType(split[1]);
            return (s1 == 2 || s1 == 4 || s1 == 3) && s2 == 2;
        } else {
            int state = getType(s);
            return state == 2 || state == 4 || state == 3;
        }
    }

    private int getType(String s) {
        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int op = op(ch);
            state = fsm[state][op];
            if (state < 0) {
                return state;
            }
        }
        return state;
    }


    private int op(char ch) {
        if (ch == '+' || ch == '-') {
            return 0;
        } else if (ch >= '0' && ch <= '9') {
            return 1;
        } else if (ch == '.') {
            return 2;
        } else {
            //一定是不合法
            return 3;
        }
    }
}
