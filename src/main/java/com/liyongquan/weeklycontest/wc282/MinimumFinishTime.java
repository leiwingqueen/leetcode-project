package com.liyongquan.weeklycontest.wc282;

import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//给你一个下标从 0 开始的二维整数数组 tires ，其中 tires[i] = [fi, ri] 表示第 i 种轮胎如果连续使用，第 x 圈需要耗时 fi * ri(x-1) 秒。
//
//比方说，如果 fi = 3 且 ri = 2 ，且一直使用这种类型的同一条轮胎，那么该轮胎完成第 1 圈赛道耗时 3 秒，完成第 2 圈耗时 3 * 2 = 6 秒，完成第 3 圈耗时 3 * 22 = 12 秒，依次类推。
//同时给你一个整数 changeTime 和一个整数 numLaps 。
//
//比赛总共包含 numLaps 圈，你可以选择 任意 一种轮胎开始比赛。每一种轮胎都有 无数条 。每一圈后，你可以选择耗费 changeTime 秒 换成 任意一种轮胎（也可以换成当前种类的新轮胎）。
//
//请你返回完成比赛需要耗费的 最少 时间。
//
//
//
//示例 1：
//
//输入：tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
//输出：21
//解释：
//第 1 圈：使用轮胎 0 ，耗时 2 秒。
//第 2 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
//第 3 圈：耗费 5 秒换一条新的轮胎 0 ，然后耗时 2 秒完成这一圈。
//第 4 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
//总耗时 = 2 + 6 + 5 + 2 + 6 = 21 秒。
//完成比赛的最少时间为 21 秒。
//示例 2：
//
//输入：tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
//输出：25
//解释：
//第 1 圈：使用轮胎 1 ，耗时 2 秒。
//第 2 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
//第 3 圈：耗时 6 秒换一条新的轮胎 1 ，然后耗时 2 秒完成这一圈。
//第 4 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
//第 5 圈：耗时 6 秒换成轮胎 0 ，然后耗时 1 秒完成这一圈。
//总耗时 = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 秒。
//完成比赛的最少时间为 25 秒。
//
//
//提示：
//
//1 <= tires.length <= 105
//tires[i].length == 2
//1 <= fi, changeTime <= 105
//2 <= ri <= 105
//1 <= numLaps <= 1000

public class MinimumFinishTime {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(tires, Comparator.comparingInt(o -> o[0]));
        //过滤掉不合法的轮胎
        list.add(tires[0]);
        for (int i = 1; i < tires.length; i++) {
            if (tires[i][1] <= tires[i - 1][1]) {
                list.add(tires[i]);
            }
        }
        int[][] t = new int[list.size()][2];
        int idx = 0;
        for (int[] tire : list) {
            t[idx][0] = tire[0];
            t[idx][1] = tire[1];
            idx++;
        }
        int res = Integer.MAX_VALUE;
        idx = 0;
        for (int[] tire : t) {
            int sub = tire[0] + backtrace(t, changeTime, numLaps, idx, 1, 1);
            res = Math.min(sub, res);
            idx++;
        }
        return res;
    }

    private int backtrace(int[][] tires, int changeTime, int numLaps, int last, int k, int lap) {
        if (lap == numLaps) {
            return 0;
        }
        //不换
        int res = backtrace(tires, changeTime, numLaps, last, k + 1, lap + 1) + tires[last][0] * (int) Math.pow(tires[last][1], k);
        //更换
        for (int i = 0; i < tires.length; i++) {
            if (i == last) {
                int sub = backtrace(tires, changeTime, numLaps, i, 1, lap + 1) + changeTime + tires[i][0];
                res = Math.min(sub, res);
            }
        }
        return res;
    }

    /**
     * 还是得看官解
     *
     * @param tires
     * @param changeTime
     * @param numLaps
     * @return
     */
    public int minimumFinishTime2(int[][] tires, int changeTime, int numLaps) {
        int mx = 17;
        //轮胎连续跑k圈的最小时间
        int[] fn = new int[mx + 1];
        for (int i = 1; i <= mx; i++) {
            //避免溢出
            fn[i] = Integer.MAX_VALUE / 2;
        }
        for (int[] tire : tires) {
            long sum = 0;
            long cur = tire[0];
            for (int i = 1; i <= mx; i++) {
                sum += cur;
                if (sum < Integer.MAX_VALUE / 2) {
                    fn[i] = Math.min((int) sum, fn[i]);
                }
                cur *= tire[1];
                if (sum >= Integer.MAX_VALUE / 2 || cur >= Integer.MAX_VALUE / 2) {
                    break;
                }
            }
        }
        //dp迭代
        int[] dp = new int[numLaps + 1];
        dp[0] = -changeTime;
        for (int i = 1; i <= numLaps; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= Math.min(i, mx); j++) {
                dp[i] = Math.min(changeTime + dp[i - j] + fn[j], dp[i]);
            }
        }
        return dp[numLaps];
    }
}
