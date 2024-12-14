package bwc145

import (
	"math"
	"sort"
)

// Bob 被困在了一个地窖里，他需要破解 n 个锁才能逃出地窖，每一个锁都需要一定的 能量 才能打开。每一个锁需要的能量存放在一个数组 strength 里，其中 strength[i] 表示打开第 i 个锁需要的能量。
//
//Bob 有一把剑，它具备以下的特征：
//
//一开始剑的能量为 0 。
//剑的能量增加因子 X 一开始的值为 1 。
//每分钟，剑的能量都会增加当前的 X 值。
//打开第 i 把锁，剑的能量需要到达 至少 strength[i] 。
//打开一把锁以后，剑的能量会变回 0 ，X 的值会增加一个给定的值 K 。
//你的任务是打开所有 n 把锁并逃出地窖，请你求出需要的 最少 分钟数。
//
//请你返回 Bob 打开所有 n 把锁需要的 最少 时间。
//
//
//
//示例 1：
//
//输入：strength = [3,4,1], K = 1
//
//输出：4
//
//解释：
//
//时间	能量	X	操作	更新后的 X
//0	0	1	什么也不做	1
//1	1	1	打开第 3 把锁	2
//2	2	2	什么也不做	2
//3	4	2	打开第 2 把锁	3
//4	3	3	打开第 1 把锁	3
//无法用少于 4 分钟打开所有的锁，所以答案为 4 。
//
//示例 2：
//
//输入：strength = [2,5,4], K = 2
//
//输出：5
//
//解释：
//
//时间	能量	X	操作	更新后的 X
//0	0	1	什么也不做	1
//1	1	1	什么也不做	1
//2	2	1	打开第 1 把锁	3
//3	3	3	什么也不做	3
//4	6	3	打开第 2 把锁	5
//5	5	5	打开第 3 把锁	7
//无法用少于 5 分钟打开所有的锁，所以答案为 5 。
//
//
//
//提示：
//
//n == strength.length
//1 <= n <= 8
//1 <= K <= 10
//1 <= strength[i] <= 106

// 贪心的思路，有问题
func findMinimumTime(strength []int, K int) int {
	sort.Ints(strength)
	x := 1
	t := 0
	for i := 0; i < len(strength); i++ {
		day := (strength[i] + x - 1) / x
		t += day
		x += K
	}
	return t
}

// 先改成朴素的dfs,这样也能过？
func findMinimumTime2(strength []int, K int) int {
	n := len(strength)
	var dfs func(strength []int, x int, i int) int
	dfs = func(strength []int, x int, i int) int {
		if i == n {
			return 0
		}
		// 选择一个处理
		res := math.MaxInt
		for j := i; j < n; j++ {
			day := (strength[j] + x - 1) / x
			strength[i], strength[j] = strength[j], strength[i]
			res = min(res, dfs(strength, x+K, i+1)+day)
			strength[i], strength[j] = strength[j], strength[i]
		}
		return res
	}
	return dfs(strength, 1, 0)
}
