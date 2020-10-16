package com.liyongquan.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 * <p>
 * 示例1:
 * <p>
 * 输入：S = "qqe"
 * 输出：["eqq","qeq","qqe"]
 * 示例2:
 * <p>
 * 输入：S = "ab"
 * 输出：["ab", "ba"]
 * 提示:
 * <p>
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-ii-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation2 {
    public String[] permutation(String S) {
        if (S.length() == 1) {
            return new String[]{S};
        }
        Map<Character, Integer> used = new HashMap<>(S.length());
        for (int i = 0; i < S.length(); i++) {
            used.put(S.charAt(i), used.getOrDefault(S.charAt(i), 0) + 1);
        }
        List<String> result = new LinkedList<>();
        dfs(new char[S.length()], 0, S.length(), used, result);
        return result.toArray(new String[]{});
    }

    private void dfs(char[] prefix, int index, int length, Map<Character, Integer> used, List<String> result) {
        if (index >= length) {
            result.add(new String(prefix));
        }
        for (Map.Entry<Character, Integer> kv : used.entrySet()) {
            if (kv.getValue() > 0) {
                prefix[index] = kv.getKey();
                kv.setValue(kv.getValue() - 1);
                dfs(prefix, index + 1, length, used, result);
                //回溯
                kv.setValue(kv.getValue() + 1);
            }
        }
    }
}
