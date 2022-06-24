package com.liyongquan.tree;

//352. 将数据流变为多个不相交区间
//给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
//
//实现 SummaryRanges 类：
//
//SummaryRanges() 使用一个空数据流初始化对象。
//void addNum(int val) 向数据流中加入整数 val 。
//int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
// 
//
//示例：
//
//输入：
//["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
//[[], [1], [], [3], [], [7], [], [2], [], [6], []]
//输出：
//[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
//
//解释：
//SummaryRanges summaryRanges = new SummaryRanges();
//summaryRanges.addNum(1);      // arr = [1]
//summaryRanges.getIntervals(); // 返回 [[1, 1]]
//summaryRanges.addNum(3);      // arr = [1, 3]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
//summaryRanges.addNum(7);      // arr = [1, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
//summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
//summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
// 
//
//提示：
//
//0 <= val <= 104
//最多调用 addNum 和 getIntervals 方法 3 * 104 次
// 
//
//进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Map;
import java.util.TreeMap;

/**
 * @author liyongquan
 * @date 2021/10/9
 */
public class SummaryRanges {
    private TreeMap<Integer, int[]> ranges;

    /**
     * 整体思路，首先合并区间的操作只会发生在相邻的区域上，我们只需要判断两个相邻区域是否会发生合并。一旦发生合并，继续寻找合并后区间的相邻区域进行合并
     * <p>
     * 区间查找我们可以使用BST来加速整个查找的过程
     */
    public SummaryRanges() {
        ranges = new TreeMap<>();
    }

    public void addNum(int val) {
        int[] range = new int[]{val, val};
        while (true) {
            Map.Entry<Integer, int[]> floor = ranges.floorEntry(range[0]);
            Map.Entry<Integer, int[]> ceil = ranges.ceilingEntry(range[0]);
            if (floor != null) {
                int[][] m1 = merge(floor.getValue(), range);
                if (m1.length == 1) {
                    range = m1[0];
                    ranges.remove(floor.getKey());
                    continue;
                }
            }
            if (ceil != null) {
                int[][] m2 = merge(ceil.getValue(), range);
                if (m2.length == 1) {
                    range = m2[0];
                    ranges.remove(ceil.getKey());
                    continue;
                }
            }
            ranges.put(range[0], range);
            break;
        }
    }

    private int[][] merge(int[] r1, int[] r2) {
        //没有交集
        if (r1[1] < r2[0] - 1 || r2[1] < r1[0] - 1) {
            return new int[][]{
                    r1, r2
            };
        }
        int l = Math.min(r1[0], r2[0]);
        int r = Math.max(r1[1], r2[1]);
        return new int[][]{{l, r}};
    }

    public int[][] getIntervals() {
        int[][] res = new int[ranges.size()][2];
        int idx = 0;
        for (int[] v : ranges.values()) {
            res[idx++] = v;
        }
        return res;
    }
}