package com.liyongquan.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * intervals[i][0] <= intervals[i][1]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeIntervals {
    /**
     * 使用左端点排序，然后再做合并
     * <p>
     * 证明？
     *
     * 时间复杂度O(nlogn)，空间复杂度O(n)
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> result = new ArrayList<>(intervals.length);
        int[] first = new int[]{intervals[0][0], intervals[0][1]};
        result.add(first);
        int[] last = first;
        for (int i = 1; i < intervals.length; i++) {
            if (last[1] >= intervals[i][0]) {
                if (last[1] < intervals[i][1]) {
                    last[1] = intervals[i][1];
                }
            } else {
                last = new int[]{intervals[i][0], intervals[i][1]};
                result.add(last);
            }
        }
        int[][] r = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }
}
