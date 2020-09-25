package com.liyongquan.dfs;

import sun.nio.cs.ext.MacHebrew;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数 n，你可以做如下操作：
 * <p>
 * 1. 如果 n 是偶数，则用 n / 2替换 n。
 * 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 8
 * <p>
 * 输出:
 * 3
 * <p>
 * 解释:
 * 8 -> 4 -> 2 -> 1
 * 示例 2:
 * <p>
 * 输入:
 * 7
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 7 -> 8 -> 4 -> 2 -> 1
 * 或
 * 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntegerReplacement {
    /**
     * 不剪枝，直接用递归解决。
     * 不通过，数据量天大，直接栈溢出
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if ((n & 1) == 0) {
            return integerReplacement(n >> 1) + 1;
        }
        return Math.min(integerReplacement(n - 1), integerReplacement(n + 1)) + 1;
    }

    /**
     * 带记忆的递归，
     * 同样超出最大栈的深度，无法通过
     *
     * @param n
     * @return
     */
    public int integerReplacement2(int n) {
        return dfs(n, new HashMap<>());
    }

    /**
     * 当最后一位为0，直接右移，count++;
     * 当最后一个为1，且上一位也为1，+1，然后count--(这样可以减少上一位的移动次数，直接一次移动把上一位也变成偶数了)
     * 否则 直接右移
     *
     * @param n
     * @return
     */
    public int integerReplacement3(int n) {
        int count = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                count++;
            } else if ((n & 2) == 0 || n == 3) {
                //3是一个特殊场景
                n++;
                count += 2;
            } else {
                n--;
                count += 2;
            }
            n >>= 1;
        }
        return count;
    }

    private int dfs(int n, Map<Integer, Integer> mem) {
        if (n == 1) {
            return 0;
        }
        if (mem.containsKey(n)) {
            return mem.get(n);
        }
        if ((n & 1) == 0) {
            int n2 = n >> 1;
            int c = dfs(n2, mem);
            mem.put(n2, c);
            return c + 1;
        }
        int c1 = dfs(n - 1, mem);
        mem.put(n - 1, c1);
        int c2 = dfs(n + 1, mem);
        mem.put(n + 1, c2);
        return Math.min(c1, c2) + 1;
    }

    public static void main(String[] args) {
        IntegerReplacement ir = new IntegerReplacement();
        /*int i = ir.integerReplacement3(8);
        System.out.println(i);
        int i1 = ir.integerReplacement3(7);
        System.out.println(i1);
        int i3 = ir.integerReplacement3(2147483647);
        System.out.println(i3);*/
        int i = ir.integerReplacement3(65535);
        System.out.println(i);
    }
}
