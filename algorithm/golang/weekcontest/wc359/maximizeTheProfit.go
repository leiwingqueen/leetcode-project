package wc359

import "sort"

// 2830. 销售利润最大化 显示英文描述
//通过的用户数1111
//尝试过的用户数1975
//用户总通过次数1201
//用户总提交次数5371
//题目难度Medium
//给你一个整数 n 表示数轴上的房屋数量，编号从 0 到 n - 1 。
//
//另给你一个二维整数数组 offers ，其中 offers[i] = [starti, endi, goldi] 表示第 i 个买家想要以 goldi 枚金币的价格购买从 starti 到 endi 的所有房屋。
//
//作为一名销售，你需要有策略地选择并销售房屋使自己的收入最大化。
//
//返回你可以赚取的金币的最大数目。
//
//注意 同一所房屋不能卖给不同的买家，并且允许保留一些房屋不进行出售。
//
//
//
//示例 1：
//
//输入：n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
//输出：3
//解释：
//有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
//将位于 [0,0] 范围内的房屋以 1 金币的价格出售给第 1 位买家，并将位于 [1,3] 范围内的房屋以 2 金币的价格出售给第 3 位买家。
//可以证明我们最多只能获得 3 枚金币。
//示例 2：
//
//输入：n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
//输出：10
//解释：有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
//将位于 [0,2] 范围内的房屋以 10 金币的价格出售给第 2 位买家。
//可以证明我们最多只能获得 10 枚金币。
//
//
//提示：
//
//1 <= n <= 105
//1 <= offers.length <= 105
//offers[i].length == 3
//0 <= starti <= endi <= n - 1
//1 <= goldi <= 103

func maximizeTheProfit(n int, offers [][]int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	sort.Slice(offers, func(i, j int) bool {
		return offers[i][0] < offers[j][0]
	})
	k := len(offers)
	dp := make([]int, k)
	dp[0] = offers[0][2]
	for i := 1; i < k; i++ {
		dp[i] = dp[i-1]
		start := offers[i][0]
		j := i - 1
		for ; j >= 0; j-- {
			if offers[j][1] < start {
				break
			}
		}
		if j >= 0 {
			dp[i] = max(dp[i], dp[j]+offers[i][2])
		} else {
			dp[i] = max(dp[i], offers[i][2])
		}
	}
	return dp[k-1]
}
