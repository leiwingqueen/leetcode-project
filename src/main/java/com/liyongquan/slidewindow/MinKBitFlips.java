package com.liyongquan.slidewindow;

import com.liyongquan.math.FactorialZeros;

/**
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 * <p>
 * 返回所需的 K 位翻转的次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [0,1,0], K = 1
 * 输出：2
 * 解释：先翻转 A[0]，然后翻转 A[2]。
 * 示例 2：
 * <p>
 * 输入：A = [1,1,0], K = 2
 * 输出：-1
 * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
 * 示例 3：
 * <p>
 * 输入：A = [0,0,0,1,0,1,1,0], K = 3
 * 输出：3
 * 解释：
 * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
 * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
 * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 30000
 * 1 <= K <= A.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinKBitFlips {
    /**
     * 没有很好的思路，先尝试每次都从第一个为0的位置的位置进行翻转
     * <p>
     * 时间复杂度O(n*k)
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int count = 0;
        for (int i = 0; i <= A.length - K; i++) {
            if (A[i] == 0) {
                count++;
                //翻转
                for (int j = i; j < i + K; j++) {
                    A[j] = A[j] == 0 ? 1 : 0;
                }
            }
        }
        //判断最后的窗口
        for (int i = A.length - K; i < A.length; i++) {
            if (A[i] == 0) {
                return -1;
            }
        }
        return count;
    }
}
