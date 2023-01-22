package wc329

import "math"

// 时间复杂度O(n^3)，超时
func minCost(nums []int, k int) int {
	n := len(nums)
	// 统计[i,j]范围内的唯一出现的数字的数量
	distinct := make([][]int, n)
	for i := 0; i < n; i++ {
		distinct[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		mp := make(map[int]int)
		sum := 0
		for j := i; j < n; j++ {
			num := nums[j]
			mp[num]++
			if mp[num] == 1 {
				sum += 1
			} else if mp[num] == 2 {
				sum -= 1
			}
			distinct[i][j] = sum
		}
	}
	// dp 初始化
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		dp[i][0] = k + (i + 1) - distinct[0][i]
	}
	for i := 1; i < n; i++ {
		for j := i; j >= 1; j-- {
			// 前i+1个数字，分成j块的最大值
			mx := math.MaxInt32
			// TODO: 这里应该如何优化呢
			for l := j; l <= i; l++ {
				sub := dp[l-1][j-1] + k + (i - l + 1) - distinct[l][i]
				if sub < mx {
					mx = sub
				}
			}
			dp[i][j] = mx
		}
	}
	res := math.MaxInt32
	for i := 0; i < n; i++ {
		if dp[n-1][i] < res {
			res = dp[n-1][i]
		}
	}
	return res
}

// 擦，跟真相很接近了，真的就差一点点
func minCost2(nums []int, k int) int {
	n := len(nums)
	// 统计[i,j]范围内的唯一出现的数字的数量
	distinct := make([][]int, n)
	for i := 0; i < n; i++ {
		distinct[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		mp := make(map[int]int)
		sum := 0
		for j := i; j < n; j++ {
			num := nums[j]
			mp[num]++
			if mp[num] == 1 {
				sum += 1
			} else if mp[num] == 2 {
				sum -= 1
			}
			distinct[i][j] = sum
		}
	}
	// dp 初始化
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		dp[i] = math.MaxInt32
		for j := 0; j < i; j++ {
			sub := dp[j] + k + (i - j) - distinct[j][i-1]
			if sub < dp[i] {
				dp[i] = sub
			}
		}
	}
	return dp[n]
}
