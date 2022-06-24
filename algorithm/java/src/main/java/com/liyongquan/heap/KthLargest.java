package com.liyongquan.heap;

/**
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 返回当前数据流中第 k 大的元素。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *  
 * <p>
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthLargest {
    private int[] heap;
    private int size;
    private int cap;

    /**
     * 维护一个小根堆即可，每次跟根节点比较，如果大于根节点就替换并做heapify
     * <p>
     * 这个题目就是经典的榜单类题目
     *
     * @param k
     * @param nums
     */
    public KthLargest(int k, int[] nums) {
        //构造小顶堆
        this.cap = k;
        heap = new int[cap];
        for (int i = 0; i < Math.min(this.cap, nums.length); i++) {
            heap[i] = nums[i];
            size++;
        }
        if (size==cap) {
            buildHeap(heap, size);
        }
        //不断更新堆
        for (int i = cap; i < nums.length; i++) {
            if (nums[i] > heap[0]) {
                heap[0] = nums[i];
                heapify(heap, 0, cap);
            }
        }
    }

    /**
     * 时间复杂度O(k)
     *
     * @param val
     * @return
     */
    public int add(int val) {
        //堆不满的场景
        if (size < this.cap) {
            heap[size++] = val;
            if (size == cap) {
                buildHeap(heap, size);
                return heap[0];
            }
            return -1;
        }
        //更新堆
        if (val <= heap[0]) {
            return heap[0];
        }
        heap[0] = val;
        heapify(heap, 0, size);
        return heap[0];
    }

    /**
     * 构建小根堆
     *
     * @param nums
     */
    private void buildHeap(int[] nums, int size) {
        //从第一个非根节点开始
        for (int i = size / 2; i >= 0; i--) {
            heapify(nums, i, size);
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
        int l = 2 * idx + 1, r = 2 * idx + 2, min = idx;
        //找到最大值
        if (l < len && nums[l] < nums[min]) {
            min = l;
        }
        if (r < len && nums[r] < nums[min]) {
            min = r;
        }
        //不需要调整
        if (min == idx) {
            return;
        }
        //继续调整对应的子树,这里也可以改成使用迭代，只是递归会更好理解
        swap(nums, idx, min);
        heapify(nums, min, len);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
