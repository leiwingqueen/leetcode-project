package dp

import (
	"math/big"
	"slices"
	"sort"
)

// 给你一个整数数组 rewardValues，长度为 n，代表奖励的值。
//
//最初，你的总奖励 x 为 0，所有下标都是 未标记 的。你可以执行以下操作 任意次 ：
//
//从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
//如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x + rewardValues[i]），并 标记 下标 i。
//以整数形式返回执行最优操作能够获得的 最大 总奖励。
//
//
//
//示例 1：
//
//输入：rewardValues = [1,1,3,3]
//
//输出：4
//
//解释：
//
//依次标记下标 0 和 2，总奖励为 4，这是可获得的最大值。
//
//示例 2：
//
//输入：rewardValues = [1,6,4,3,2]
//
//输出：11
//
//解释：
//
//依次标记下标 0、2 和 1。总奖励为 11，这是可获得的最大值。
//
//
//
//提示：
//
//1 <= rewardValues.length <= 5 * 104
//1 <= rewardValues[i] <= 5 * 104

// 无脑dfs，超时
func maxTotalReward(rewardValues []int) int {
	n := len(rewardValues)
	var dfs func(rewards []int, x int, idx int) int
	dfs = func(rewards []int, x int, idx int) int {
		if idx == n {
			return 0
		}
		res := 0
		for i := idx; i < n; i++ {
			r := rewards[i]
			if r > x {
				rewards[i], rewards[idx] = rewards[idx], rewards[i]
				d := dfs(rewards, x+r, idx+1) + r
				res = max(res, d)
				rewards[i], rewards[idx] = rewards[idx], rewards[i]
			}
		}
		return res
	}
	return dfs(rewardValues, 0, 0)
}

// 假设先做好排序，能否优化一下
func maxTotalReward2(rewardValues []int) int {
	sort.Slice(rewardValues, func(i, j int) bool {
		return rewardValues[i] > rewardValues[j]
	})
	n := len(rewardValues)
	var dfs func(x int, idx int) int
	dfs = func(x int, idx int) int {
		if idx == n || rewardValues[idx] <= x {
			return 0
		}
		// 这里就可以简化成选和不选
		res := max(dfs(x, idx+1), dfs(x+rewardValues[idx], idx+1)+rewardValues[idx])
		return res
	}
	return dfs(0, 0)
}

// 首先选择一定是从小往大选择,还是超时
func maxTotalReward3(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	var dfs func(x int, idx int) int
	dfs = func(x int, idx int) int {
		if idx == n {
			return 0
		}
		// 不选
		res := dfs(x, idx+1)
		// 选择
		if rewardValues[idx] > x {
			res = max(res, dfs(x+rewardValues[idx], idx+1)+rewardValues[idx])
		}
		return res
	}
	return dfs(0, 0)
}

// 无脑增加记忆，还是超时
func maxTotalReward4(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	mem := make(map[int]map[int]int)
	var dfs func(x int, idx int) int
	dfs = func(x int, idx int) int {
		if idx == n {
			return 0
		}
		if _, ok := mem[idx]; !ok {
			mem[idx] = make(map[int]int)
		}
		if v, ok := mem[idx][x]; ok {
			return v
		}
		// 不选
		res := dfs(x, idx+1)
		// 选择
		if rewardValues[idx] > x {
			res = max(res, dfs(x+rewardValues[idx], idx+1)+rewardValues[idx])
		}
		mem[idx][x] = res
		return res
	}
	return dfs(0, 0)
}

// 改写成dp
func maxTotalReward5(rewardValues []int) int {
	n := len(rewardValues)
	sort.Ints(rewardValues)
	mx := 2*rewardValues[n-1] - 1
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, mx+1)
	}
	dp[0][0] = true
	dp[0][rewardValues[0]] = true
	for i := 0; i < n; i++ {
		dp[i][0] = true
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= mx; j++ {
			dp[i][j] = dp[i-1][j]
			if rewardValues[i] <= j && j-rewardValues[i] < rewardValues[i] {
				dp[i][j] = dp[i][j] || dp[i-1][j-rewardValues[i]]
			}
		}
	}
	res := 0
	for i := 0; i <= mx; i++ {
		if dp[n-1][i] {
			res = i
		}
	}
	return res
}

// 疯了，这个优化
func maxTotalReward6(rewardValues []int) int {
	rewardValues = slices.Compact(rewardValues)
	n := len(rewardValues)
	sort.Ints(rewardValues)
	one := big.NewInt(1)
	pre := big.NewInt(1)
	tmp := new(big.Int)
	for i := 0; i < n; i++ {
		v := rewardValues[i]
		mask := tmp.Sub(tmp.Lsh(one, uint(v)), one)
		tmp.Lsh(tmp.And(pre, mask), uint(v))
		pre.Or(pre, tmp)
	}
	return pre.BitLen() - 1
}
