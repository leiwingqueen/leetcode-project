package com.liyongquan.dp;

//1345. 跳跃游戏 IV
//给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
//
//每一步，你可以从下标 i 跳到下标：
//
//i + 1 满足：i + 1 < arr.length
//i - 1 满足：i - 1 >= 0
//j 满足：arr[i] == arr[j] 且 i != j
//请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
//
//注意：任何时候你都不能跳到数组外面。
//
// 
//
//示例 1：
//
//输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
//输出：3
//解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
//示例 2：
//
//输入：arr = [7]
//输出：0
//解释：一开始就在最后一个元素处，所以你不需要跳跃。
//示例 3：
//
//输入：arr = [7,6,9,6,9,6,9,7]
//输出：1
//解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
//示例 4：
//
//输入：arr = [6,1,9]
//输出：2
//示例 5：
//
//输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
//输出：3
// 
//
//提示：
//
//1 <= arr.length <= 5 * 10^4
//-10^8 <= arr[i] <= 10^8
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/jump-game-iv
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

public class MinJumpsIV {
    /**
     * 参考Dijkstra
     * <p>
     * 先通过这个规则构造图
     *
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        //构造相同value的下标的列表
        int len = arr.length;
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!mp.containsKey(arr[i])) {
                mp.put(arr[i], new LinkedList<>());
            }
            mp.get(arr[i]).add(i);
        }
        Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>();
        s1.add(len - 1);
        for (int i = 0; i < len - 1; i++) {
            s2.add(i);
        }
        int[] path = new int[len];
        for (int i = 0; i < len - 1; i++) {
            path[i] = Integer.MAX_VALUE;
        }
        int idx = len - 1;
        while (!s1.contains(0)) {
            //更新和idx相邻的节点
            int x = idx - 1;
            if (x >= 0 && s2.contains(x)) {
                path[x] = Math.min(path[idx] + 1, path[x]);
            }
            x = idx + 1;
            if (x < len && s2.contains(x)) {
                path[x] = Math.min(path[idx] + 1, path[x]);
            }
            List<Integer> list = mp.getOrDefault(arr[idx], new ArrayList<>());
            for (Integer y : list) {
                if (y != idx) {
                    path[y] = Math.min(path[idx] + 1, path[y]);
                }
            }
            //得到s2中的最小值
            int minIdx = 0;
            int minDis = Integer.MAX_VALUE;
            for (Integer p : s2) {
                if (path[p] < minDis) {
                    minDis = path[p];
                    minIdx = p;
                }
            }
            if (minDis == Integer.MAX_VALUE) {
                return -1;
            }
            idx = minIdx;
            s1.add(idx);
            s2.remove(idx);
        }
        return path[0];
    }
}
