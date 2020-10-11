package com.liyongquan.dfs;

import com.liyongquan.util.RightBinarySearch;

import java.util.*;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Kuohao {
    /**
     * 回溯法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        return dfs("", n, n, new LinkedList<>());
    }

    private List<String> dfs(String prefix, int left, int right, Deque<Character> stack) {
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
            stack.push('(');
        }
        return result;
    }


    /**
     * 其实我上面的解法跟官方的剪枝是一样的，只是官方可以不使用栈来判断合法性
     * <p>
     * 合法的括号其实不需要栈也可以判断，只要在任意一个位置(i)上，i之前的所有左括号均多于右括号即合法。
     * 反过来就可以认为在任意位点，剩余左括号的数量 一定 要小于 剩余的右括号
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> list = new LinkedList<>();
        dfs(new char[2 * n], 0, n, n, list);
        return list;
    }

    private void dfs(char[] prefix, int index, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(new String(prefix));
        }
        if (left > 0) {
            prefix[index] = '(';
            dfs(prefix, index + 1, left - 1, right, result);
        }
        if (left < right) {
            prefix[index] = ')';
            dfs(prefix, index + 1, left, right - 1, result);
        }
    }
}
