package com.liyongquan.greedy;

import java.util.Arrays;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 注意:
 * <p>
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * <p>
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: [ [1,2], [1,2], [1,2] ]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * <p>
 * 输入: [ [1,2], [2,3] ]
 * <p>
 * 输出: 0
 * <p>
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EraseOverlapIntervals {
    /**
     * 类似安排会议室。先统一进行排序。优先根据start_time,然后再根据end_time
     * <p>
     * 如果相邻两个区域冲突。
     * 有3种场景：
     * 1.pre.end_time==next.end_time
     * 保留next。next是pre的子区间
     * 2.pre.end_time>next.end_time
     * 保留next。next是pre的子区间
     * 3.pre.end_time<next.end_time
     * 保留pre
     *
     * 问题：如何证明？
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int[] pre = intervals[0];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            //判断是否冲突
            if (pre[1] > interval[0]) {
                count++;
                if (pre[1] >= interval[1]) {
                    pre = interval;
                }
            } else {
                pre = interval;
            }
        }
        return count;
    }
}
