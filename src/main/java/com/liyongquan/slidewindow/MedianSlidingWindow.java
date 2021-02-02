package com.liyongquan.slidewindow;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 480. 滑动窗口中位数
 * <p>
 * <p>
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * <p>
 * 例如：
 * <p>
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * <p>
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianSlidingWindow {
    /**
     * 先尝试维护一个k窗口的排序好的数组，每次移动窗口相当于要做一次insert和delete，我们尝试用linklist来进行
     * <p>
     * 每次插入和删除的删除的复杂度都是O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        //初始化第一个个窗口
        List<Integer> window = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            window.add(nums[k]);
        }
        //排序
        Collections.sort(window);
        double[] res = new double[nums.length - k + 1];
        int l = 0;
        while (l <= nums.length - k) {
            //获取中位数
            double middle = 0D;
            if (k % 2 == 1) {
                middle = window.get(l + k / 2);
            } else {
                middle = ((double) (window.get(l + k / 2 - 1) + window.get(l + k / 2 + 1))) / 2;
            }
            res[l] = middle;
            //更新窗口的数据
            if (l < nums.length - k) {
                //增加窗口右边的值
                Iterator<Integer> it = window.iterator();
                int idx = 0;
                while (it.hasNext() && it.next() < nums[l + k]) {
                    idx++;
                }
                window.add(idx, nums[l + k]);
                //删除窗口左边的数字
                it = window.iterator();
                idx = 0;
                while (it.hasNext() && it.next() != nums[l]) {
                    idx++;
                }
                window.remove(idx);
            }
            l++;
        }
        return res;
    }
}
