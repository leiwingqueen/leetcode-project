package com.liyongquan.binarySort;

//你是一名行政助理，手里有两位客户的空闲时间表：slots1 和 slots2，以及会议的预计持续时间 duration，请你为他们安排合适的会议时间。
//
//「会议时间」是两位客户都有空参加，并且持续时间能够满足预计时间 duration 的 最早的时间间隔。
//
//如果没有满足要求的会议时间，就请返回一个 空数组。
//
// 
//
//「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，表示从 start 开始，到 end 结束。 
//
//题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要么 start1 > end2，要么 start2 > end1。
//
// 
//
//示例 1：
//
//输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
//输出：[60,68]
//示例 2：
//
//输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
//输出：[]
// 
//
//提示：
//
//1 <= slots1.length, slots2.length <= 10^4
//slots1[i].length, slots2[i].length == 2
//slots1[i][0] < slots1[i][1]
//slots2[i][0] < slots2[i][1]
//0 <= slots1[i][j], slots2[i][j] <= 10^9
//1 <= duration <= 10^6 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/meeting-scheduler
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinAvailableDuration {
    /**
     * 这个双指针其实不那么容易想到
     * <p>
     * java的双指针写法有点恶心，还是go的写法比较优雅
     *
     * @param slots1
     * @param slots2
     * @param duration
     * @return
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(slots2, Comparator.comparingInt(o -> o[0]));
        int p1 = 0, p2 = 0;
        while (p1 < slots1.length && p2 < slots2.length) {
            //判断两个slot是否有交集
            int[] s1 = slots1[p1];
            int[] s2 = slots2[p2];
            if (s1[0] < s2[0]) {
                if (s1[1] <= s2[0]) {
                    //没有交集
                    p1++;
                } else if (s1[1] >= s2[1]) {
                    //s1包含s2
                    if (s2[1] - s2[0] >= duration) {
                        return Arrays.asList(s2[0], s2[1]);
                    } else {
                        p2++;
                    }
                } else {
                    //有交集
                    if (s1[1] - s2[0] >= duration) {
                        return Arrays.asList(s2[0], s1[1]);
                    } else {
                        p1++;
                    }
                }
            } else {
                if (s2[1] <= s1[0]) {
                    //没有交集
                    p2++;
                } else if (s1[1] >= s2[1]) {
                    //s1包含s2
                    if (s1[1] - s1[0] >= duration) {
                        return Arrays.asList(s1[0], s1[1]);
                    } else {
                        p1++;
                    }
                } else {
                    //有交集
                    if (s2[1] - s1[0] >= duration) {
                        return Arrays.asList(s1[0], s2[1]);
                    } else {
                        p2++;
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
