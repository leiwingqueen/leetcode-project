package com.liyongquan.math;

//172. 阶乘后的零
//给定一个整数 n ，返回 n! 结果中尾随零的数量。
//
//提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
//
// 
//
//示例 1：
//
//输入：n = 3
//输出：0
//解释：3! = 6 ，不含尾随 0
//示例 2：
//
//输入：n = 5
//输出：1
//解释：5! = 120 ，有一个尾随 0
//示例 3：
//
//输入：n = 0
//输出：0
// 
//
//提示：
//
//0 <= n <= 104
// 
//
//进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class TrailingZeroes {
    /**
     * 核心是计算数字里面包含的2和5的因子有多少对
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int[] cnt = new int[2];
        for (int i = 2; i <= n; i++) {
            int[] check = check(i);
            cnt[0] += check[0];
            cnt[1] += check[1];
        }
        return Math.min(cnt[0], cnt[1]);
    }

    private int[] check(int num) {
        int[] res = new int[2];
        while (num > 0 && num % 2 == 0) {
            res[0]++;
            num /= 2;
        }
        while (num > 0 && num % 5 == 0) {
            res[1]++;
            num /= 5;
        }
        return res;
    }
}
