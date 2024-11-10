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
		// 关键在于如何简化这个循环
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

func sumOfGoodSubsequences2(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	if n <= 0 {
		return 0
	}
	mp1 := make(map[int]int)
	mp2 := make(map[int]int)
	mp1[nums[0]] = 1
	mp2[nums[0]] = nums[0]
	for i := 1; i < n; i++ {
		dp0 := 1
		dp1 := nums[i]
		k1 := nums[i] - 1
		if v, ok := mp1[k1]; ok {
			dp0 += mp1[k1]
			dp1 = (dp1 + mp2[k1] + v*nums[i]) % mod
		}
		k2 := nums[i] + 1
		if v, ok := mp1[k2]; ok {
			dp0 += mp1[k2]
			dp1 = (dp1 + mp2[k2] + v*nums[i]) % mod
		}
		mp1[nums[i]] += dp0
		mp2[nums[i]] = (mp2[nums[i]] + dp1) % mod
	}
	res := 0
	for _, v := range mp2 {
		res = (res + v) % mod
	}
	return res
}
