package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 * <p>
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 * <p>
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：wall = [[1],[1],[1]]
 * 输出：3
 *  
 * 提示：
 * <p>
 * n == wall.length
 * 1 <= n <= 104
 * 1 <= wall[i].length <= 104
 * 1 <= sum(wall[i].length) <= 2 * 104
 * 对于每一行 i ，sum(wall[i]) 应当是相同的
 * 1 <= wall[i][j] <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/brick-wall
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class LeastBricks {
    /**
     * n=wall.size()
     * k=wall[i].size();
     * sum[wall[i]]=m
     * <p>
     * 时间复杂度O(m*n*log(k))
     * <p>
     * 超时
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        //计算前缀和
        List<int[]> preSum = new ArrayList<>(wall.size());
        for (List<Integer> list : wall) {
            int[] pre = new int[list.size()];
            Iterator<Integer> it = list.iterator();
            pre[0] = it.next();
            int idx = 1;
            while (it.hasNext()) {
                pre[idx] = pre[idx - 1] + it.next();
                idx++;
            }
            preSum.add(pre);
        }
        int min = wall.size();
        int right = preSum.get(0)[preSum.get(0).length - 1];
        for (int i = 1; i < right; i++) {
            //计算经过的墙的数量
            int cnt = 0;
            for (int[] list : preSum) {
                if (!binarySearch(list, i)) {
                    cnt++;
                }
            }
            //log.info("i:{},cnt:{}", i, cnt);
            min = Math.min(min, cnt);
        }
        return min;
    }

    private boolean binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    /**
     * 等价于计算重叠次数最多的砖块边缘的坐标
     *
     * @param wall
     * @return
     */
    public int leastBricks2(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        //计算前缀和
        int max = 0;
        for (List<Integer> list : wall) {
            int sum = 0;
            int idx = 0;
            Iterator<Integer> iterator = list.iterator();
            while (idx < list.size() - 1) {
                sum += iterator.next();
                int cnt = map.getOrDefault(sum, 0) + 1;
                map.put(sum, cnt);
                max = Math.max(max, cnt);
                idx++;
            }
        }
        return wall.size() - max;
    }
}
