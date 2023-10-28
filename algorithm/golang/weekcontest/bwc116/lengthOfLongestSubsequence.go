package bwc116

func lengthOfLongestSubsequence(nums []int, target int) int {
	n := len(nums)
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, target+1)
	}
	dp[0][0] = 0
	for i := 1; i <= target; i++ {
		dp[0][i] = -1
	}
	for i := 1; i <= n; i++ {
		dp[i][0] = 0
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= target; j++ {
			dp[i][j] = dp[i-1][j]
			if j >= nums[i-1] && dp[i-1][j-nums[i-1]] >= 0 &&
				(dp[i][j] < 0 || dp[i-1][j-nums[i-1]]+1 > dp[i][j]) {
				dp[i][j] = dp[i-1][j-nums[i-1]] + 1
			}
		}
	}
	return dp[n][target]
}
