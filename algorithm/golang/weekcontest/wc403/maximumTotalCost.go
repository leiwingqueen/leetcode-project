package wc403

import "math"

// 超时
func maximumTotalCost(nums []int) int64 {
	n := len(nums)
	dp := make([]int64, n+1)
	for i := 1; i <= n; i++ {
		sum := int64(0)
		dp[i] = math.MinInt64
		for j := i - 1; j >= 0; j-- {
			sum = int64(nums[j]) - sum
			dp[i] = max(dp[i], dp[j]+sum)
		}
	}
	return dp[n]
}

func maximumTotalCost2(nums []int) int64 {
	n := len(nums)
	dp0, dp1 := make([]int64, n), make([]int64, n)
	dp0[0] = int64(nums[0])
	dp1[0] = math.MinInt64
	for i := 1; i < n; i++ {
		dp0[i] = max(dp0[i-1], dp1[i-1]) + int64(nums[i])
		dp1[i] = dp0[i-1] - int64(nums[i])
	}
	return max(dp0[n-1], dp1[n-1])
}
