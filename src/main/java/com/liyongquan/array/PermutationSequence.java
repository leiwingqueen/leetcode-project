package com.liyongquan.array;

import java.util.*;

/**
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 * <p>
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 * <p>
 * 输入：n = 3, k = 1
 * 输出："123"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 9
 * 1 <= k <= n!
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<String> list = new ArrayList<>();
        dfs(n, new char[n], new int[n], 0, list, k);
        return list.get(list.size() - 1);
    }

    private void dfs(int n, char[] path, int[] used, int depth, List<String> list, int k) {
        //回溯解法得出的解本来就是升序的，我们只需要对输出的结果剪枝就可以了
        if (list.size() == k) {
            return;
        }
        if (depth == n) {
            //深度复制，避免回溯的时候把这个list清空
            list.add(new String(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i] == 1) {
                continue;
            }
            path[depth] = (char) ('1' + i);
            used[i] = 1;
            dfs(n, path, used, depth + 1, list, k);
            used[i] = 0;
        }
    }
}
