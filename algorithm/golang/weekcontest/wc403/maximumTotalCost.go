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
