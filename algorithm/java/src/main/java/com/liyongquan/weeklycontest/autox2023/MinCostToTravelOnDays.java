package com.liyongquan.weeklycontest.autox2023;

// 航空公司向经常乘坐飞机的乘客们提供了一些商务套票，tickets[i] = [duration_i, price_i]，表示第 i 种套票的有效天数和价格。
//
//例如：乘客购买了有效天数为 n 的套票，则该套票在第 date ~ date+n-1 天期间都可以使用。
//
//现有一名乘客将在未来的几天中出行，days[i] 表示他第 i 次出行的时间，如果他选择购买商务套票，请返回他将花费的最少金额。
//
//注意：
//
//输入不存在多个有效天数相同的套票。
//示例 1：
//
//输入：
//days = [1,2,3,4]
//tickets = [[1,3],[2,5],[3,7]]
//
//输出: 10
//
//解释：可以买一张一天有效期的票和一张三天有效期的票；或买两张两天有效期的票；总票价均为10
//
//示例 2：
//
//输入：
//days = [1,4,5]
//tickets = [[1,4],[5,6],[2,5]]
//
//输出: 6
//
//解释：买一张 5 天有效期的票；总票价为6
//
//提示：
//
//1 <= days.length <= 10^5
//1 <= days[i] < days[i+1] <= 10^9
//1 <= tickets.length <= 20
//1 <= tickets[i][0] <= 10^5
//1 <= tickets[i][1] <= 10^9

public class MinCostToTravelOnDays {
    public long minCostToTravelOnDays(int[] days, int[][] tickets) {
        int n = days.length;
        int m = tickets.length;
        long[] dp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                int day = tickets[j][0];
                int price = tickets[j][1];
                int d = days[i - 1] - day;
                int k = search(days, d);
                dp[i] = Math.min(dp[k + 1] + price, dp[i]);
            }
        }
        return dp[n];
    }

    //找到最后一个下标days[j]<=d
    private int search(int[] days, int d) {
        if (days[0] > d) {
            return -1;
        }
        int l = 0, r = days.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (days[mid] <= d) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
