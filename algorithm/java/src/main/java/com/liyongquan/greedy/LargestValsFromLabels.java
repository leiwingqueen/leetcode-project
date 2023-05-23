package com.liyongquan.greedy;

// 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。
//
//从 n 个元素中选择一个子集 s :
//
//子集 s 的大小 小于或等于 numWanted 。
//s 中 最多 有相同标签的 useLimit 项。
//一个子集的 分数 是该子集的值之和。
//
//返回子集 s 的最大 分数 。
//
// 
//
//示例 1：
//
//输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
//输出：9
//解释：选出的子集是第一项，第三项和第五项。
//示例 2：
//
//输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
//输出：12
//解释：选出的子集是第一项，第二项和第三项。
//示例 3：
//
//输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
//输出：16
//解释：选出的子集是第一项和第四项。
// 
//
//提示：
//
//n == values.length == labels.length
//1 <= n <= 2 * 104
//0 <= values[i], labels[i] <= 2 * 104
//1 <= numWanted, useLimit <= n
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-values-from-labels
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

public class LargestValsFromLabels {
    // 居然通过了，但是写法真的不敢恭维
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, List<Integer>> typeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = values[i];
            int l = labels[i];
            if (!typeMap.containsKey(l)) {
                typeMap.put(l, new ArrayList<>());
            }
            typeMap.get(l).add(v);
        }
        for (List<Integer> value : typeMap.values()) {
            value.sort((o1, o2) -> o2 - o1);
        }
        Map<Integer, Integer> pointer = new HashMap<>();
        for (Integer k : typeMap.keySet()) {
            pointer.put(k, 0);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Integer k : typeMap.keySet()) {
            List<Integer> list = typeMap.get(k);
            if (list.size() > 0) {
                pq.add(new int[]{k, list.get(0)});
            }
        }
        int c = 0;
        int res = 0;
        while (c < numWanted) {
            if (pq.size() == 0) {
                return res;
            }
            int[] poll = pq.poll();
            int label = poll[0];
            int value = poll[1];
            res += value;
            counter.put(label, counter.getOrDefault(label, 0) + 1);
            if (counter.get(label) < useLimit) {
                List<Integer> list = typeMap.get(label);
                pointer.put(label, pointer.get(label) + 1);
                if (pointer.get(label) < list.size()) {
                    pq.add(new int[]{label, list.get(pointer.get(label))});
                }
            }
            c++;
        }
        return res;
    }
}
