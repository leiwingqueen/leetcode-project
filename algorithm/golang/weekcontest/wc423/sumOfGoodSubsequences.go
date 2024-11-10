package wc423

func sumOfGoodSubsequences(nums []int) int {
	mod := 1_000_000_007
	abs := func(num int) int {
		if num < 0 {
			return -num
		} else {
			return num
		}
	}
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 2)
	}
	dp[0][0] = 1
	dp[0][1] = nums[0]
	for i := 1; i < n; i++ {
		dp[i][0] = 1
		dp[i][1] = nums[i]
		for j := i - 1; j >= 0; j-- {
			if abs(nums[j]-nums[i]) == 1 {
				dp[i][0] += dp[j][0]
				dp[i][1] = (dp[i][1] + dp[j][1] + (dp[j][0]*nums[i])%mod) % mod
			}
		}
	}
	res := 0
	for i := 0; i < n; i++ {
		res = (res + dp[i][1]) % mod
	}
	return res
}
