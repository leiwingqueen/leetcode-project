package wc404

// 理解错
func maximumLength2(nums []int, k int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, k)
	}
	dp[0][nums[0]%k] = 1
	for i := 1; i < n; i++ {
		for j := 0; j < k; j++ {
			dp[i][j] = dp[i-1][j]
			if nums[i]%k == j {
				dp[i][j] = max(dp[i][j], dp[i-1][(k-nums[i]%k)%k]+1)
			}
		}
	}
	res := 0
	for i := 0; i < k; i++ {
		res = max(res, dp[n-1][i])
	}
	return res
}

// 修复2
func maximumLength3(nums []int, k int) int {
	n := len(nums)
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, k)
		for j := 0; j < k; j++ {
			dp[i][j] = make([]int, k)
		}
	}
	dp[1][(nums[0]+nums[1])%k][nums[1]%k] = 2
	for i := 2; i < n; i++ {
		for j := 0; j < k; j++ {
			for l := 0; l < k; l++ {
				dp[i][j][l] = dp[i-1][j][l]
				if nums[i]%k == l {
					dp[i][j][l] = max(dp[i][j][l], dp[i-1][j][(j+k-l)%k]+1)
				}
			}
		}
	}
	res := 0
	for i := 0; i < k; i++ {
		for j := 0; j < k; j++ {
			res = max(res, dp[n-1][i][j])
		}
	}
	return res
}
