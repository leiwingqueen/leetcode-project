package com.liyongquan.slidewindow;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSlidingWindow {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度O((n-k+1)*k),n是数组的长度，k是滑动窗口的长度
     * <p>
     * 空间复杂度O(1)
     * <p>
     * 不通过，超时
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] r = new int[len];
        for (int i = 0; i < len; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            r[i] = max;
        }
        return r;
    }

    /**
     * 试试优先级队列？(堆排序)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length - k + 1;
        Queue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        int[] r = new int[len];
        int left = 0, right = 0, idx = 0;
        while (right - left < k - 1) {
            queue.add(nums[right]);
            right++;
        }
        while (left < len) {
            queue.add(nums[right]);
            r[idx] = queue.peek();
            right++;
            queue.remove(nums[left]);
            left++;
            idx++;
        }
        return r;
    }
}
