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
    /**
     * 还是继续回溯解法，但是时间复杂度依然是指数级别的
     *
     * @param n
     * @param k
     * @return
     */
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

    /**
     * 如果k=n!，相当于我们要找到最后才能找到对应的解的，时间效率很低。
     * <p>
     * 我们先考虑边界情况，如果k=i!,其中1<=i<=n。那么我们直接把第i位之前(低位为1)的所有数字倒序，i位以后的数字按1,2,...i-1升序。
     * eg.n=3,k=2。则k=2!
     * 结果为132。高位升序，低位倒序
     * <p>
     * 我们考虑，如果i-1!<k<i!，则意味着我们需要从第i位(低位为1)开始变更
     * eg.n=3,k=3。
     * 2!<k<3!。则我们需要从第3位开始选择，因为前两位不管我们怎么选择，最后的可能性只有123和132两种。
     * 那么第i位我们如何选择？
     * m=k/(i-1)!+1
     * <p>
     * 然后继续对低位进行同样处理，但是k要减去高位已经排除掉的解
     * k=k-m*(i-1)!
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
        //先计算n!的所有解
        int[] p = new int[n + 1];
        p[0] = 0;
        p[1] = 1;
        for (int i = 2; i <= n; i++) {
            p[i] = p[i - 1] * i;
        }
        int i = 1;
        while (k < p[i] && i <= n) {
            i++;
        }
        //k==i!，倒序输出解
        if (k == p[i]) {
            StringBuilder sb = new StringBuilder(n);
            for (int j = n; j >= 1; j--) {
                sb.append((char) ('0' + j));
            }
            return sb.toString();
        }
    }
}
