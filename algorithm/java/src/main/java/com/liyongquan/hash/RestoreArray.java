package com.liyongquan.hash;

//1743. 从相邻元素对还原数组

//存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
//
//给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
//
//题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
//
//返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
//
// 
//
//示例 1：
//
//输入：adjacentPairs = [[2,1],[3,4],[3,2]]
//输出：[1,2,3,4]
//解释：数组的所有相邻元素对都在 adjacentPairs 中。
//特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
//示例 2：
//
//输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
//输出：[-2,4,1,-3]
//解释：数组中可能存在负数。
//另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
//示例 3：
//
//输入：adjacentPairs = [[100000,-100000]]
//输出：[100000,-100000]
// 
//
//提示：
//
//nums.length == n
//adjacentPairs.length == n - 1
//adjacentPairs[i].length == 2
//2 <= n <= 105
//-105 <= nums[i], ui, vi <= 105
//题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import javafx.util.Pair;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/7/25
 */
public class RestoreArray {
    /**
     * 超时
     *
     * @param adjacentPairs
     * @return
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(adjacentPairs[0][0]);
        deque.offerLast(adjacentPairs[0][1]);
        Set<Pair<Integer, Integer>> remain = new HashSet<>();
        for (int i = 1; i < n - 1; i++) {
            remain.add(new Pair<>(adjacentPairs[i][0], adjacentPairs[i][1]));
        }
        while (remain.size() > 0) {
            Pair<Integer, Integer> match = findMatch(deque, remain);
            remain.remove(match);
        }
        int[] res = new int[n];
        int idx = 0;
        while (!deque.isEmpty()) {
            res[idx++] = deque.pollFirst();
        }
        return res;
    }

    private Pair<Integer, Integer> findMatch(Deque<Integer> deque, Set<Pair<Integer, Integer>> remain) {
        for (Pair<Integer, Integer> pair : remain) {
            int n1 = pair.getKey();
            int n2 = pair.getValue();
            if (n1 == deque.peekFirst()) {
                deque.offerFirst(n2);
                return pair;
            } else if (n1 == deque.peekLast()) {
                deque.offerLast(n2);
                return pair;
            } else if (n2 == deque.peekFirst()) {
                deque.offerFirst(n1);
                return pair;
            } else if (n2 == deque.peekLast()) {
                deque.offerLast(n1);
                return pair;
            }
        }
        //由于题意一定存在可行解，所以不可能会到这里
        return null;
    }

    /**
     * 构造无向无环图，然后找到第一个节点，遍历一次即可
     *
     * @param adjacentPairs
     * @return
     */
    public int[] restoreArray2(int[][] adjacentPairs) {
        int len = adjacentPairs.length;
        int n = len + 1;
        Map<Integer, List<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> counter = new HashMap();
        for (int[] pair : adjacentPairs) {
            if (!edges.containsKey(pair[0])) {
                edges.put(pair[0], new ArrayList<>());
            }
            if (!edges.containsKey(pair[1])) {
                edges.put(pair[1], new ArrayList<>());
            }
            edges.get(pair[0]).add(pair[1]);
            edges.get(pair[1]).add(pair[0]);
            counter.put(pair[0], counter.getOrDefault(pair[0], 0) + 1);
            counter.put(pair[1], counter.getOrDefault(pair[1], 0) + 1);
        }
        //找到起始点
        int start = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue().intValue() == 1) {
                start = entry.getKey();
                break;
            }
        }
        //遍历
        int[] res = new int[n];
        int idx = 0;
        int last = 100001;
        int cur = start;
        while (idx < n) {
            res[idx++] = cur;
            List<Integer> endList = edges.get(cur);
            for (Integer end : endList) {
                if (end == last) {
                    continue;
                } else {
                    last = cur;
                    cur = end;
                    break;
                }
            }
        }
        return res;
    }
}
