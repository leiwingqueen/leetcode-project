package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;

/**
 * 978. 最长湍流子数组
 * <p>
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * <p>
 * 返回 A 的最大湍流子数组的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * <p>
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[100]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class MaxTurbulenceSize {
    /**
     * 滑动窗口算法
     *
     * @param arr
     * @return
     */
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length <= 1) {
            return arr.length;
        }
        //最后两个数是否大于比较
        boolean lt = arr[0] > arr[1];
        int l = 0, r = 1;
        int res = 1;
        while (r < arr.length) {
            //窗口右移
            if (l == r - 1 && arr[r - 1] != arr[r] || (lt && arr[r - 1] < arr[r]) || (!lt && arr[r - 1] > arr[r])) {
                log.info("窗口右移...l:{},r:{}", l, r + 1);
                lt = arr[r - 1] > arr[r];
                //更新结果
                r++;
                res = Math.max(r - l, res);
            } else {
                log.info("窗口左移...l:{},r:{}", r, r + 1);
                //左边界移动
                lt = arr[r - 1] > arr[r];
                l = r - 1;
                r++;
            }
        }
        return res;
    }
}
