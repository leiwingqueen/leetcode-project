package wc402

import "sort"

// 典型的01背包
// 先排序
// f(n)为前n个数字构成的最大总伤害，f(n)=max(f(n-1),f(k)+power[n-1]),其中k为power[k]<=power[n-1]的最大值
func maximumTotalDamage(power []int) int64 {
	sort.Ints(power)
	n := len(power)
	dp := make([]int64, n)
	dp[0] = int64(power[0])
	for i := 1; i < n; i++ {
		idx := sort.Search(n, func(j int) bool {
			return power[j] >= power[i]-2
		})
		dp[i] = max(dp[i-1], int64(power[i]))
		if idx > 0 {
			dp[i] = max(dp[i], dp[idx-1]+int64(power[i]))
		}
	}
	return dp[n-1]
}
