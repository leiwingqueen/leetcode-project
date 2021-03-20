package com.liyongquan.heap;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 面试题 17.14. 最小K个数
 * <p>
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 * <p>
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestK {
    /**
     * 当然首选是堆排啦
     * <p>
     * 时间复杂度O(nlog(k))
     * 空间复杂度O(k)
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK(int[] arr, int k) {
        int len = arr.length;
        if (k == 0) {
            return new int[]{};
        }
        if (len == 0 || arr.length <= k) {
            return arr;
        }
        //构造大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.add(num);
            } else {
                if (num < pq.peek()) {
                    pq.poll();
                    pq.add(num);
                }
            }
        }
        //输出结果
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }


    /**
     * 快排的思路
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK2(int[] arr, int k) {
        int len = arr.length;
        if (k == 0) {
            return new int[]{};
        }
        if (len == 0 || arr.length <= k) {
            return arr;
        }
        int l = 0, r = len - 1;
        while (l < r) {
            int pivot = partition(arr, l, r);
            if (pivot == k - 1) {
                break;
            } else if (pivot < k - 1) {
                l = pivot + 1;
            } else {
                r = pivot - 1;
            }
        }
        //输出
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private int partition(int[] arr, int l, int r) {
        //随机一个pivot节点
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int pivot = random.nextInt(l, r + 1);
        swap(arr, l, pivot);
        pivot = l;
        l += 1;
        while (l < r) {
            //左右边界分别移动
            while (l < r && arr[l] < arr[pivot]) {
                l++;
            }
            while (l < r && arr[r] >= arr[pivot]) {
                r--;
            }
            if (l < r) {
                swap(arr, l, r);
            }
        }
        swap(arr, pivot, l);
        return l;
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
