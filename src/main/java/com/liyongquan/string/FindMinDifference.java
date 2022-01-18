package com.liyongquan.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//539. 最小时间差
//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
//
// 
//
//示例 1：
//
//输入：timePoints = ["23:59","00:00"]
//输出：1
//示例 2：
//
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
// 
//
//提示：
//
//2 <= timePoints <= 2 * 104
//timePoints[i] 格式为 "HH:MM"
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-time-difference
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class FindMinDifference {
    /**
     * 排序后计算
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        timePoints.sort(String::compareTo);
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size(); i++) {
            String cur = timePoints.get(i);
            String next = timePoints.get((i + 1) % timePoints.size());
            int cal = cal(cur, next);
            diff = Math.min(diff, cal);
        }
        return diff;
    }

    private int cal(String t1, String t2) {
        if (t1.compareTo(t2) > 0) {
            String tmp = t1;
            t1 = t2;
            t2 = tmp;
        }
        String[] s1 = t1.split(":");
        int p1 = Integer.parseInt(s1[0]) * 60 + Integer.parseInt(s1[1]);
        String[] s2 = t2.split(":");
        int p2 = Integer.parseInt(s2[0]) * 60 + Integer.parseInt(s2[1]);
        return Math.min(p2 - p1, p1 + 60 * 24 - p2);
    }
}
