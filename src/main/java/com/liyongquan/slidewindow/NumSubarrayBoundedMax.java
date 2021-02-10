package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;

/**
 * 795. 区间子数组个数
 * <p>
 * 给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。
 * <p>
 * 求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。
 * <p>
 * 例如 :
 * 输入:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * 输出: 3
 * 解释: 满足条件的子数组: [2], [2, 1], [3].
 * 注意:
 * <p>
 * L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
 * 数组 A 的长度范围在[1, 50000]。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class NumSubarrayBoundedMax {
    /**
     * 滑动窗口
     * <p>
     * 需要维护整个子数组的最大值。
     * 这个其实不好做，但是我们实际上只需要把整个数组的范围划分为3个区间。
     * [0,L),[L,R],(R,10^9]
     * <p>
     * 对于每个区间的子数组我们需要保证[L,R]至少有一个数字，且(R,10^9]没有一个数字
     * <p>
     * 不通过...
     *
     * @param A
     * @param L
     * @param R
     * @return
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int len = A.length;
        if (len == 0 || L > R) {
            return 0;
        }
        int l = 0, r = 0;
        int res = 0;
        int[] freq = new int[3];
        while (r < len) {
            //右指针移动
            int idx = getIdx(A[r], L, R);
            freq[idx]++;
            r++;
            log.info("右指针移动...r:{}", r);
            //左指针移动
            while (l < r && !(freq[2] == 0 && freq[1] >= 1)) {
                int id = getIdx(A[l], L, R);
                freq[id]--;
                l++;
                log.info("左指针移动...l:{}", l);
            }
            res += r - l;
        }
        return res;
    }

    private int getIdx(int num, int L, int R) {
        if (num < L) {
            return 0;
        } else if (num >= L && num <= R) {
            return 1;
        } else {
            return 2;
        }
    }

    public int numSubarrayBoundedMax2(int[] A, int L, int R) {
        return maxBounder(A, R) - maxBounder(A, L - 1);
    }

    private int maxBounder(int[] A, int bound) {
        int len = A.length;
        int l = 0, r = 0;
        int res = 0;
        while (r < len) {
            r++;
            if (A[r - 1] <= bound) {
                res += r - l;
            } else {
                l = r;
            }
        }
        return res;
    }
}
