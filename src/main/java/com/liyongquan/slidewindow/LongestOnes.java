package com.liyongquan.slidewindow;

/**
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * <p>
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * <p>
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestOnes {
    /**
     * 滑动窗口算法
     * <p>
     * 窗口向右扩展:r++
     * 窗口平移：l++,r++
     * <p>
     * 窗口大小只扩张不缩小，最终窗口大小就是我们的结果集
     * <p>
     * 我们判断当前窗口是否满足解，只需要判断当前窗口1的个数+k是否>=窗口大小
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int l = 0, r = 0;
        int count = 0, max = 0;
        while (r < A.length) {
            if (A[r] == 1) {
                count++;
            }
            r++;
            if (count + K >= r - l) {
                max = Math.max(max, r - l);
            } else {
                //窗口平移
                if (A[l] == 1) {
                    count--;
                }
                l++;
            }
        }
        return max;
    }
}
