package com.liyongquan.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinations {
    private String[] map = new String[]{
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits.length()==0) {
            return Collections.emptyList();
        }
        return dfs("", digits, 0);
    }

    private List<String> dfs(String prefix, String digits, int start) {
        if (start >= digits.length()) {
            return Arrays.asList(prefix);
        }
        int pos = digits.charAt(start) - '0' - 2;
        List<String> result = new ArrayList<>(map[pos].length());
        for (int i = 0; i < map[pos].length(); i++) {
            result.addAll(dfs(prefix + map[pos].charAt(i), digits, start + 1));
        }
        return result;
    }
}
