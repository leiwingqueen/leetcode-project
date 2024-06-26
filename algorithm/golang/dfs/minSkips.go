package dfs

import "math"

// 给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。道路的长度用一个长度为 n 的整数数组 dist 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
//
// 当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。
//
// 例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
// 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。
//
// 例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
// 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
//
// 示例 1：
//
// 输入：dist = [1,3,2], speed = 4, hoursBefore = 2
// 输出：1
// 解释：
// 不跳过任何休息时间，你将用 (1/4 + 3/4) + (3/4 + 1/4) + (2/4) = 2.5 小时才能抵达会议现场。
// 可以跳过第 1 次休息时间，共用 ((1/4 + 0) + (3/4 + 0)) + (2/4) = 1.5 小时抵达会议现场。
// 注意，第 2 次休息时间缩短为 0 ，由于跳过第 1 次休息时间，你是在整数小时处完成通过第 2 条道路。
// 示例 2：
//
// 输入：dist = [7,3,5,5], speed = 2, hoursBefore = 10
// 输出：2
// 解释：
// 不跳过任何休息时间，你将用 (7/2 + 1/2) + (3/2 + 1/2) + (5/2 + 1/2) + (5/2) = 11.5 小时才能抵达会议现场。
// 可以跳过第 1 次和第 3 次休息时间，共用 ((7/2 + 0) + (3/2 + 0)) + ((5/2 + 0) + (5/2)) = 10 小时抵达会议现场。
// 示例 3：
//
// 输入：dist = [7,3,5,5], speed = 1, hoursBefore = 10
// 输出：-1
// 解释：即使跳过所有的休息时间，也无法准时参加会议。
//
// 提示：
//
// n == dist.length
// 1 <= n <= 1000
// 1 <= dist[i] <= 105
// 1 <= speed <= 106
// 1 <= hoursBefore <= 107

// 简单的回溯解法，超时
func minSkips(dist []int, speed int, hoursBefore int) int {
	n := len(dist)
	preSum := make([]int, n+1)
	for i, d := range dist {
		preSum[i+1] = preSum[i] + d
	}
	// 前n条路，需要在前k个小时完成
	var dfs func(n int, k int) int
	dfs = func(n int, k int) int {
		if n == 0 {
			return 0
		}
		if n == 1 {
			if (dist[0]+speed-1)/speed <= k {
				return 0
			} else {
				return -1
			}
		}
		res := -1
		for i := 0; i < n; i++ {
			// [i,n)的时间，取下界即可
			t := (preSum[n] - preSum[i] + speed - 1) / speed
			if k >= t {
				sub := dfs(i, k-t)
				if sub >= 0 && (res < 0 || res > sub+n-i-1) {
					res = sub + n - i - 1
				}
			}
		}
		return res
	}
	return dfs(n, hoursBefore)
}

// 增加记忆，还是超时
func minSkips2(dist []int, speed int, hoursBefore int) int {
	n := len(dist)
	preSum := make([]int, n+1)
	for i, d := range dist {
		preSum[i+1] = preSum[i] + d
	}
	// 前n条路，需要在前k个小时完成
	mem := make(map[int64]int)
	buildKey := func(n, k int) int64 {
		return int64(n)<<32 | int64(k)
	}
	var dfs func(n int, k int) int
	dfs = func(n int, k int) int {
		if n == 0 {
			return 0
		}
		if n == 1 {
			if (dist[0]+speed-1)/speed <= k {
				return 0
			} else {
				return -1
			}
		}
		if v, ok := mem[buildKey(n, k)]; ok {
			return v
		}
		res := -1
		for i := n - 1; i >= 0; i-- {
			// [i,n)的时间，取下界即可
			t := (preSum[n] - preSum[i] + speed - 1) / speed
			// 提前剪枝
			if k < t {
				break
			}
			sub := dfs(i, k-t)
			if sub >= 0 && (res < 0 || res > sub+n-i-1) {
				res = sub + n - i - 1
			}
		}
		mem[buildKey(n, k)] = res
		return res
	}
	return dfs(n, hoursBefore)
}

// 由于hours的数据量大，我们换个思路做dp
// f(i,j)为前i段路，跳过j次的最短的时间,其中j<i
// if j==0,表示没有跳过，直接计算
// if j>0. f(i,j)=min{f(i-k,j-1)+t[i-k:i]},其中j-1<i-k，则k<i-j+1
func minSkips3(dist []int, speed int, hoursBefore int) int {
	// 可忽略误差,最难的应该是这个误差的处理
	const EPS = 1e-7
	n := len(dist)
	preSum := make([]int, n+1)
	for i, d := range dist {
		preSum[i+1] = preSum[i] + d
	}
	dp := make([][]float64, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]float64, n)
	}
	// dp初始化
	for i := 1; i <= n; i++ {
		dp[i][0] = math.Ceil(dp[i-1][0]) + float64(dist[i-1])/float64(speed) - EPS
	}
	// dp迭代
	for i := 2; i <= n; i++ {
		for j := 1; j < i; j++ {
			// 跳过
			dp[i][j] = dp[i-1][j-1] + float64(dist[i-1])/float64(speed) - EPS
			// 不跳过
			if j < i-1 {
				dp[i][j] = min(dp[i][j], math.Ceil(dp[i-1][j])+float64(dist[i-1])/float64(speed)-EPS)
			}
		}
	}
	// 检测所有满足条件的情况
	for i := 0; i < n; i++ {
		if dp[n][i] <= float64(hoursBefore) {
			return i
		}
	}
	return -1
}
