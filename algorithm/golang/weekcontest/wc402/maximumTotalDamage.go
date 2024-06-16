package wc402

import "sort"

// 典型的01背包
// 先排序
// f(n)为前n个数字构成的最大总伤害，f(n)=max(f(n-1),f(k)+power[n-1]),其中k为power[k]<=power[n-1]的最大值
// 错误，缺少处理重复的数字
func maximumTotalDamage(power []int) int64 {
	sort.Ints(power)
	n := len(power)
	dp := make([]int64, n)
	dp[0] = int64(power[0])
	for i := 1; i < n; i++ {
		dp[i] = max(dp[i-1], int64(power[i]))
		if power[i] == power[i-1] {
			dp[i] = max(dp[i], dp[i-1]+int64(power[i]))
		} else {
			idx := sort.Search(n, func(j int) bool {
				return power[j] >= power[i]-2
			})
			if idx > 0 {
				dp[i] = max(dp[i], dp[idx-1]+int64(power[i]))
			}
		}
	}
	return dp[n-1]
}

func maximumTotalDamage2(power []int) int64 {
	mp := make(map[int]int)
	for _, p := range power {
		mp[p]++
	}
	var arr [][]int
	for k, v := range mp {
		arr = append(arr, []int{k, v})
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] < arr[j][0]
	})
	k := len(arr)
	dp := make([]int64, k)
	dp[0] = int64(arr[0][0] * arr[0][1])
	for i := 1; i < k; i++ {
		dp[i] = max(dp[i-1], int64(arr[i][0])*int64(arr[i][1]))
		idx := sort.Search(k, func(j int) bool {
			return arr[j][0] >= arr[i][0]-2
		})
		if idx > 0 {
			dp[i] = max(dp[i], dp[idx-1]+int64(arr[i][0])*int64(arr[i][1]))
		}
	}
	return dp[k-1]
}
