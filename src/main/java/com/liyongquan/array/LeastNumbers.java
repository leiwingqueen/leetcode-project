package com.liyongquan.array;

import java.util.*;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 *
 * 限制：
 *
 *     0 <= k <= arr.length <= 10000
 *     0 <= arr[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeastNumbers {
    /**
     * 使用map
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        List<Integer> list=new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        Collections.sort(list);
        int[] r=new int[k];
        for (int i = 0; i < k; i++) {
            r[i]=list.get(i);
        }
        return r;
    }

    /**
     * 使用大根堆
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k<=0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue=new PriorityQueue<>((t1, t2) -> t2-t1);
        for (int i : arr) {
            if (queue.size()<k) {
                queue.offer(i);
            }else if(i<queue.peek()){
                queue.poll();
                queue.offer(i);
            }
        }
        int[] r=new int[k];
        for (int i = 0; i < k; i++) {
            r[i]=queue.poll();
        }
        return r;
    }

    public static void main(String[] args) {
        LeastNumbers leastNumbers=new LeastNumbers();
        int[] result = leastNumbers.getLeastNumbers2(new int[]{0, 0, 0, 2,5}, 2);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
