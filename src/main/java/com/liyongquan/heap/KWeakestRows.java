package com.liyongquan.heap;

//给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
//
//请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
//
//如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
//
//军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
//
// 
//
//示例 1：
//
//输入：mat =
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]],
//k = 3
//输出：[2,0,3]
//解释：
//每行中的军人数目：
//行 0 -> 2
//行 1 -> 4
//行 2 -> 1
//行 3 -> 2
//行 4 -> 5
//从最弱到最强对这些行排序后得到 [2,0,3,1,4]
//示例 2：
//
//输入：mat =
//[[1,0,0,0],
// [1,1,1,1],
// [1,0,0,0],
// [1,0,0,0]],
//k = 2
//输出：[0,2]
//解释：
//每行中的军人数目：
//行 0 -> 1
//行 1 -> 4
//行 2 -> 1
//行 3 -> 1
//从最弱到最强对这些行排序后得到 [0,2,3,1]
// 
//
//提示：
//
//m == mat.length
//n == mat[i].length
//2 <= n, m <= 100
//1 <= k <= m
//matrix[i][j] 不是 0 就是 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/8/1
 */
public class KWeakestRows {
    public int[] kWeakestRows(int[][] mat, int k) {
        int len = mat.length;
        //预处理，避免重复计算军队的人数
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = countArmy(mat[i]);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int c1 = arr[o1];
            int c2 = arr[o2];
            if (c1 != c2) {
                return c2 - c1;
            }
            return o2 - o1;
        });
        for (int i = 0; i < len; i++) {
            //序号一定比前面的大
            if (pq.size() >= k && arr[i] < arr[pq.peek()]) {
                pq.poll();
            }
            pq.add(i);
        }
        Deque<Integer> deque = new LinkedList<>();
        while (pq.size() > 0) {
            deque.offerFirst(pq.poll());
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }

    private int countArmy(int[] arr) {
        int cnt = 0;
        int idx = 0;
        while (idx < arr.length && arr[idx] == 1) {
            cnt++;
            idx++;
        }
        return cnt;
    }
}