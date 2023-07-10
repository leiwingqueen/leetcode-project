package wc353

func maximumJumps(nums []int, target int) int {
	n := len(nums)
	dp := make([]int, n)
	for i := 1; i < n; i++ {
		// 从小标j直接到i
		dp[i] = -1
		for j := 0; j < i; j++ {
			if nums[j]-nums[i] >= -target && nums[j]-nums[i] <= target &&
				dp[j] >= 0 && (dp[i] < 0 || dp[j]+1 > dp[i]) {
				dp[i] = dp[j] + 1
			}
		}
	}
	return dp[n-1]
}
