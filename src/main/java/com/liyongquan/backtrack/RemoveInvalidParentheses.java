package com.liyongquan.backtrack;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 301. 删除无效的括号
 * <p>
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * <p>
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 * 示例 2:
 * <p>
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 * 示例 3:
 * <p>
 * 输入: ")("
 * 输出: [""]
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 * 示例 2：
 * <p>
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 * 示例 3：
 * <p>
 * 输入：s = ")("
 * 输出：[""]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();
        int lCnt = 0;
        //需要删除的左右括号数量
        int delLCnt = 0, delRCnt = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                continue;
            }
            if (s.charAt(i) == '(') {
                lCnt++;
            } else {
                if (lCnt > 0) {
                    lCnt--;
                } else {
                    delRCnt++;
                }
            }
        }
        delLCnt += lCnt;
        int len2 = len - delLCnt - delRCnt;
        if (len2 == 0) {
            return Arrays.asList("");
        }
        return backtrace(arr, new char[len2], 0, 0, len, len2, 0, 0, delLCnt, delRCnt);
    }

    private List<String> backtrace(char[] arr, char[] path, int idx1, int idx2,
                                   int len1, int len2, int lCnt, int rCnt,
                                   int delLCnt, int delRCnt) {
        if (idx1 == len1) {
            return lCnt == rCnt ? Collections.emptyList() : Arrays.asList(new String(path));
        }
        if (arr[idx1] >= 'a' && arr[idx1] <= 'z') {
            path[idx2] = arr[idx1];
            return backtrace(arr, path, idx1 + 1, idx2 + 1, len1, len2, lCnt, rCnt, delLCnt, delRCnt);
        }
        List<String> res = new LinkedList<>();
        if (arr[idx1] == '{') {
            path[idx2] = '{';
            res.addAll(backtrace(arr, path, idx1 + 1, idx2 + 1, len1, len2, lCnt + 1, rCnt, delLCnt, delRCnt));
            if (delLCnt > 0) {
                //删除左括号
                res.addAll(backtrace(arr, path, idx1 + 1, idx2, len1, len2, lCnt, rCnt, delLCnt - 1, delRCnt));
            }
        } else {
            if (lCnt < rCnt) {
                path[idx2] = '}';
                res.addAll(backtrace(arr, path, idx1 + 1, idx2 + 1, len1, len2, lCnt, rCnt + 1, delLCnt, delRCnt));
            }
            //删除右括号
            if (delRCnt > 0) {
                res.addAll(backtrace(arr, path, idx1 + 1, idx2, len1, len2, lCnt, rCnt, delLCnt, delRCnt - 1));
            }
        }
        return res;
    }
}
