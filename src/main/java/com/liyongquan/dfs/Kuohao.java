package com.liyongquan.dfs;

import java.util.*;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Kuohao {
    public List<String> generateParenthesis(int n) {
        return dfs("",n,n,new Stack<>());
    }

    private List<String> dfs(String prefix, int left, int right, Stack<Character> stack) {
        if (left <= 0 && right <= 0) {
            if (stack.isEmpty()) {
                return Arrays.asList(prefix);
            } else {
                return Collections.emptyList();
            }
        }
        List<String> result = new ArrayList<>();
        if (left > 0) {
            stack.add('(');
            result.addAll(dfs(prefix + '(', left - 1, right, stack));
            stack.pop();
        }
        if (right > 0 && !stack.isEmpty()) {
            stack.pop();
            result.addAll(dfs(prefix + ')', left, right - 1, stack));
            stack.push(')');
        }
        return result;
    }
}
