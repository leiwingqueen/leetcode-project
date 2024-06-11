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

// 假设f(n,x)为前n个数字的和<=x的最大值
// f(n,x)=max(f(n-1,x),f(n-1,x-a[n-1]-1)+a[n-1])
// 最终我们需要求的是f(n,s)，假设s为数组rewardValues的总和
// 当n==0，值为0
func maxTotalReward3(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	sum := 0
	for _, v := range rewardValues {
		sum += v
	}
	mx := max(sum, 2*rewardValues[n-1])
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, mx+1)
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= mx; j++ {
			dp[i][j] = dp[i-1][j]
			// 找到最后一个下标k,k<rewardValues[i-1]
			// 也就是k<rewardValues[i-1]
			// 其中k<=j
			if j-rewardValues[i-1] >= 0 {
				if j-rewardValues[i-1] < rewardValues[i-1] {
					dp[i][j] = max(dp[i][j], dp[i-1][j-rewardValues[i-1]]+rewardValues[i-1])
				} else {
					dp[i][j] = max(dp[i][j], dp[i-1][rewardValues[i-1]-1]+rewardValues[i-1])
				}
			}
		}
	}
	return dp[n][mx]
}
