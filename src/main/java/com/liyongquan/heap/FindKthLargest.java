package com.liyongquan.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindKthLargest {
    /**
     * 最简单的做法，排序后直接取第k个数
     * <p>
     * 时间复杂度O(nlogn),空间复杂度O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 堆排
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        //大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.length, (o1, o2) -> o2 - o1);
        for (int num : nums) {
            pq.add(num);
        }
        int res = -1;
        for (int i = 0; i < k; i++) {
            res = pq.poll();
        }
        return res;
    }

    /**
     * 自己实现堆排
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest3(int[] nums, int k) {
        //大根堆
        buildHeap(nums);
        for (int i = nums.length - 1; i >= nums.length - k; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
        return nums[nums.length - k];
    }

    /**
     * 构建大根堆
     *
     * @param nums
     */
    private void buildHeap(int[] nums) {
        //从第一个非根节点开始
        for (int i = nums.length / 2; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
    }

    /**
     * 调整
     *
     * @param nums
     * @param idx
     * @param len
     */
    private void heapify(int[] nums, int idx, int len) {
        if (idx >= len) {
            return;
        }
        //左右子树
        int l = 2 * idx + 1, r = 2 * idx + 2, max = idx;
        //找到最大值
        if (l < len && nums[l] > nums[max]) {
            max = l;
        }
        if (r < len && nums[r] > nums[max]) {
            max = r;
        }
        //不需要调整
        if (max == idx) {
            return;
        }
        //继续调整对应的子树,这里也可以改成使用迭代，只是递归会更好理解
        swap(nums, idx, max);
        heapify(nums, max, len);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //TODO:快排还有优化的空间，可以直接打到log(n)的性能


}
