package com.liyongquan.dp;

//630. 课程表 III
//这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
//
//你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
//
//返回你最多可以修读的课程数目。
//
// 
//
//示例 1：
//
//输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
//输出：3
//解释：
//这里一共有 4 门课程，但是你最多可以修 3 门：
//首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
//第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
//第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
//第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
//示例 2：
//
//输入：courses = [[1,2]]
//输出：1
//示例 3：
//
//输入：courses = [[3,2],[4,3]]
//输出：0
// 
//
//提示:
//
//1 <= courses.length <= 104
//1 <= durationi, lastDayi <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/course-schedule-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/12/14
 */
public class ScheduleCourse {
    /**
     * 先来个回溯解法
     *
     * 超时
     *
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        //过滤掉不合法的数据
        List<int[]> list = new ArrayList<>(courses.length);
        for (int[] cs : courses) {
            if (cs[0] <= cs[1]) {
                list.add(cs);
            }
        }
        if (list.size() <= 0) {
            return 0;
        }
        //倒序排序
        list.sort((o1, o2) -> o2[1] - o1[1]);
        return backtrace(list, 0, list.get(0)[1]);
    }

    private int backtrace(List<int[]> courses, int idx, int lastDay) {
        if (idx == courses.size() || lastDay == 0) {
            return 0;
        }
        //不选择
        int r1 = backtrace(courses, idx + 1, lastDay);
        int[] cs = courses.get(idx);
        int last = Math.min(cs[1], lastDay) - cs[0];
        if (last >= 0) {
            //选择
            int r2 = backtrace(courses, idx + 1, last) + 1;
            if (r2 > r1) {
                r1 = r2;
            }
        }
        return r1;
    }
}
