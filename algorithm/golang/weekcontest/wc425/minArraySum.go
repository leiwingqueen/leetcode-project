package wc425

// 典型的DP
func minArraySum(nums []int, k int, op1 int, op2 int) int {
	n := len(nums)
	dp := make([][][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([][]int, op1+1)
		for j := 0; j <= op1; j++ {
			dp[i][j] = make([]int, op2+1)
		}
	}
	for i := 1; i <= n; i++ {
		for j := 0; j <= op1; j++ {
			for l := 0; l <= op2; l++ {
				// 两个都不操作
				dp[i][j][l] = dp[i-1][j][l] + nums[i-1]
				if j > 0 {
					dp[i][j][l] = min(dp[i-1][j-1][l]+(nums[i-1]+1)/2, dp[i][j][l])
				}
				if l > 0 && nums[i-1] >= k {
					dp[i][j][l] = min(dp[i-1][j][l-1]+nums[i-1]-k, dp[i][j][l])
				}
				if j > 0 && l > 0 {
					// 先操作1再操作2
					if (nums[i-1]+1)/2-k >= 0 {
						dp[i][j][l] = min(dp[i-1][j-1][l-1]+(nums[i-1]+1)/2-k, dp[i][j][l])
					}
					if nums[i-1]-k >= 0 {
						dp[i][j][l] = min(dp[i-1][j-1][l-1]+(nums[i-1]-k+1)/2, dp[i][j][l])
					}
				}
			}
		}
	}
	return dp[n][op1][op2]
}
