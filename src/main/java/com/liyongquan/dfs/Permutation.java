package com.liyongquan.dfs;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.*;

/**
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 * <p>
 * 示例1:
 * <p>
 * 输入：S = "qwe"
 * 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
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
 * 链接：https://leetcode-cn.com/problems/permutation-i-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation {
    /**
     * dfs和bfs解法都是OK的，这里先用dfs。
     * <p>
     * 时间复杂度O(n!)，空间复杂度O(1)
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {
        if (S.length() == 1) {
            return new String[]{S};
        }
        int[] bitmap1 = new int[26], bitmap2 = new int[26];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) >= 'a') {
                bitmap1[S.charAt(i) - 'a']++;
            } else {
                bitmap2[S.charAt(i) - 'A']++;
            }
        }
        List<String> result = new LinkedList<>();
        dfs(new char[S.length()], 0, S.length(), bitmap1, bitmap2, result);
        return result.toArray(new String[]{});
    }

    private void dfs(char[] prefix, int index, int length, int[] bitmap1, int[] bitmap2, List<String> result) {
        if (index >= length) {
            result.add(new String(prefix));
        }
        for (int i = 0; i < bitmap1.length; i++) {
            if (bitmap1[i] > 0) {
                prefix[index] = (char) (i + 'a');
                bitmap1[i]--;
                dfs(prefix, index + 1, length, bitmap1, bitmap2, result);
                //回溯
                bitmap1[i]++;
            }
        }
        for (int i = 0; i < bitmap2.length; i++) {
            if (bitmap2[i] > 0) {
                prefix[index] = (char) (i + 'A');
                bitmap2[i]--;
                dfs(prefix, index + 1, length, bitmap1, bitmap2, result);
                //回溯
                bitmap2[i]++;
            }
        }
    }


    /**
     * 上面基础上稍微优化了一下
     *
     * @param S
     * @return
     */
    public String[] permutation2(String S) {
        if (S.length() == 1) {
            return new String[]{S};
        }
        Map<Character, Boolean> used = new HashMap<>(S.length());
        for (int i = 0; i < S.length(); i++) {
            used.put(S.charAt(i), false);
        }
        List<String> result = new LinkedList<>();
        dfs2(new char[S.length()], 0, S.length(), used, result);
        return result.toArray(new String[]{});
    }

    private void dfs2(char[] prefix, int index, int length, Map<Character, Boolean> used, List<String> result) {
        if (index >= length) {
            result.add(new String(prefix));
        }
        for (Map.Entry<Character, Boolean> kv : used.entrySet()) {
            if (!kv.getValue()) {
                prefix[index] = kv.getKey();
                kv.setValue(true);
                dfs2(prefix, index + 1, length, used, result);
                //回溯
                kv.setValue(false);
            }
        }
    }

    public static void main(String[] args) {
        Permutation pm = new Permutation();
        String[] qwes = pm.permutation2("qwe");
        for (String qwe : qwes) {
            System.out.println(qwe);
        }
    }
}
