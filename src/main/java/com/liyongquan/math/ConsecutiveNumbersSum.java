package com.liyongquan.math;

//给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。 
//
// 
//
//示例 1:
//
//输入: n = 5
//输出: 2
//解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
//示例 2:
//
//输入: n = 9
//输出: 3
//解释: 9 = 4 + 5 = 2 + 3 + 4
//示例 3:
//
//输入: n = 15
//输出: 4
//解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
// 
//
//提示:
//
//1 <= n <= 109​​​​​​​
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/consecutive-numbers-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int n) {
        int res = 0;
        for (int i = 1; i <= Math.sqrt(2 * n); i++) {
            if (check(n, i)) {
                res++;
            }
        }
        return res;
    }

    private boolean check(int n, int k) {
        if (k > n) {
            return false;
        }
        int l = 1;
        int r = n - k + 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int sum = (mid + mid + k - 1) * k / 2;
            if (sum == n) {
                return true;
            } else if (sum < n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
