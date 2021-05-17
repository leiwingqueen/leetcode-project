package com.liyongquan.heap;

import java.util.*;

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
     * 终于让我写出来了
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK3(int[] arr, int k) {
        dfs(arr, 0, arr.length - 1, k);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void dfs(int[] arr, int l, int r, int k) {
        if (k == 0 || r - l + 1 < k || r - l + 1 == k) {
            return;
        }
        int p = partition(arr, l, r);
        int len = p - l + 1;
        if (len == k) {
            return;
        } else if (len > k) {
            //缩小1位
            dfs(arr, l, p - 1, k);
        } else {
            dfs(arr, p + 1, r, k - len);
        }
    }

    private int partition(int[] arr, int l, int r) {
        //随机一个pivot节点
        //int pivot = ThreadLocalRandom.current().nextInt(l, r + 1);
        //swap(arr, l, pivot);
        //取第一个节点作为pivot
        int pivot = arr[l];
        while (l < r) {
            //左右边界分别移动
            while (l < r && arr[r] > pivot) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
                l++;
            }
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
                r--;
            }
        }
        arr[l] = pivot;
        return l;
    }
}
