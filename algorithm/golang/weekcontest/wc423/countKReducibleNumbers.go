package wc423

import "math/bits"

func countKReducibleNumbers(s string, k int) int {
	// 先用dp提前计算n个1对应要做几次变换
	dp := make([]int, len(s)+1)
	dp[1] = 1
	for i := 2; i <= len(s); i++ {
		dp[i] = dp[bits.OnesCount(uint(i))] + 1
	}
	n := len(s)
	// 下面用digit dp来计算<s的分别做t次变换的数字有多少
	var dfs func(idx int, limit bool, t int, left int) int
	dfs = func(idx int, limit bool, t int, left int) int {
		if idx == n {
			if limit || dp[left] != t {
				return 0
			} else {
				return 1
			}
		}
		res := 0
		if limit {
			for i := byte('0'); i <= s[idx]; i++ {
				if i == '1' {
					res += dfs(idx+1, i == s[idx], t, left+1)
				} else {
					res += dfs(idx+1, i == s[idx], t, left)
				}
			}
			return res
		} else {
			for i := byte('0'); i <= '1'; i++ {
				if i == '1' {
					res += dfs(idx+1, false, t, left+1)
				} else {
					res += dfs(idx+1, false, t, left)
				}
			}
		}
		return res
	}
	res := 0
	for i := 0; i <= k; i++ {
		res += dfs(0, true, i, 0)
	}
	return res
}
