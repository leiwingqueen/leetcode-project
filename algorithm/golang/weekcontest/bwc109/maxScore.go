package bwc109

// 必然超时
func maxScore(nums []int, x int) int64 {
	n := len(nums)
	dp := make([]int64, n)
	dp[0] = int64(nums[0])
	res := int64(nums[0])
	for i := 1; i < n; i++ {
		for j := i - 1; j >= 0; j-- {
			s := dp[j] + int64(nums[i])
			if nums[i]%2 != nums[j]%2 {
				s -= int64(x)
			}
			if s > dp[i] {
				dp[i] = s
			}
		}
		if dp[i] > res {
			res = dp[i]
		}
	}
	return res
}

// 优化解法
func maxScore2(nums []int, x int) int64 {
	max := func(a, b int64) int64 {
		if a > b {
			return a
		} else {
			return b
		}
	}
	n := len(nums)
	dp := make([][]int64, 2)
	for i := 0; i < 2; i++ {
		dp[i] = make([]int64, n)
	}
	if nums[0]%2 == 0 {
		dp[0][0] = int64(nums[0])
		dp[1][0] = int64(-x)
	} else {
		dp[0][0] = int64(-x)
		dp[1][0] = int64(nums[0])
	}
	for i := 1; i < n; i++ {
		// 不选
		dp[0][i] = dp[0][i-1]
		dp[1][i] = dp[1][i-1]
		// 选择
		if nums[i]%2 == 0 {
			dp[0][i] = max(dp[0][i-1], dp[1][i-1]-int64(x)) + int64(nums[i])
		} else {
			dp[1][i] = max(dp[1][i-1], dp[0][i-1]-int64(x)) + int64(nums[i])
		}
	}
	return max(dp[0][n-1], dp[1][n-1])
}
