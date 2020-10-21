package com.liyongquan.recursion;

import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 * <p>
 * 返回的长度需要从小到大排列。
 * <p>
 * 示例 1
 * <p>
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： [3,4,5,6]
 * 解释：
 * 可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。
 * 提示：
 * <p>
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diving-board-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DivingBoard {
    /**
     * 递归解法
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        Set<Integer> set = dfs(shorter, longer, k);
        int[] result = new int[set.size()];
        int i = 0;
        for (Integer integer : set) {
            result[i++] = integer;
        }
        return result;
    }

    private Set<Integer> dfs(int shorter, int longer, int k) {
        Set<Integer> result = new HashSet<>();
        if (k == 1) {
            result.add(shorter);
            result.add(longer);
            return result;
        }
        Set<Integer> r = dfs(shorter, longer, k - 1);
        for (Integer item : r) {
            result.add(item + shorter);
            result.add(item + longer);
        }
        return result;
    }

    public static void main(String[] args) {
        DivingBoard db = new DivingBoard();
        int[] result = db.divingBoard(1, 2, 3);
        for (int i : result) {
            System.out.println(i + ",");
        }
    }
}
