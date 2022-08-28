package com.liyongquan.math;


// f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
//
//例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
//给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
//
// 
//
//示例 1：
//
//输入：k = 0
//输出：5
//解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
//示例 2：
//
//输入：k = 5
//输出：0
//解释：没有匹配到这样的 x!，符合 k = 5 的条件。
//示例 3:
//
//输入: k = 3
//输出: 5
// 
//
//提示:
//
//0 <= k <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class PreimageSizeFZF {
    // https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/solution/by-ac_oier-pk9g/
    // 还是没理解
    public int preimageSizeFZF(int k) {
        if (k <= 1) {
            return 5;
        }
        return calculate(k) - calculate(k - 1);
    }

    private int calculate(int k) {
        long l = 0, r = (long) 1e10;
        while (l < r) {
            long mid = l + (r - l + 1) / 2;
            if (getCnt(mid) <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (int) r;
    }

    private long getCnt(long x) {
        long ans = 0;
        while (x != 0) {
            ans += x / 5;
            x /= 5;
        }
        return ans;
    }
}
