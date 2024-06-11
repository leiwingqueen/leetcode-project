package wc401

import "sort"

// 贪心
func maxTotalReward(rewardValues []int) int {
	sort.Ints(rewardValues)
	res := 0
	for _, v := range rewardValues {
		if v > res {
			res += v
		}
	}
	return res
}

// 或者假设f(n)为前n个选择的最大和，f(n)=f(0),f(1)
// 错误，这种解法
func maxTotalReward2(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	dp := make([]int, n+1)
	dp[0] = 0
	for i := 1; i <= n; i++ {
		dp[i] = 0
		for j := 0; j < i; j++ {
			if dp[j] < rewardValues[i-1] {
				dp[i] = max(dp[i], dp[j]+rewardValues[i-1])
			} else {
				dp[i] = max(dp[i], dp[j])
			}
		}
	}
	return dp[n]
}
