package dfs

import (
	"math"
	"sort"
)

// 勉强通过
func minCost(n int, cuts []int) int {
	mem := make(map[int]map[int]int)
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if _, ok := mem[i]; !ok {
			mem[i] = make(map[int]int)
		}
		if v, ok := mem[i][j]; ok {
			return v
		}
		res := math.MaxInt
		finalPart := true
		for _, cut := range cuts {
			if cut > i && cut < j {
				cost := dfs(i, cut) + dfs(cut, j) + j - i
				res = min(res, cost)
				finalPart = false
			}
		}
		if finalPart {
			res = 0
		}
		mem[i][j] = res
		return res
	}
	return dfs(0, n)
}

func minCost2(n int, cuts []int) int {
	sort.Ints(cuts)
	arr := make([]int, 0, len(cuts)+2)
	arr = append(arr, 0)
	arr = append(arr, cuts...)
	arr = append(arr, n)
	k := len(arr)
	dp := make([][]int, k-1)
	for i := 0; i < k-1; i++ {
		dp[i] = make([]int, k)
	}
	dp[k-2][k-1] = 0
	for i := k - 3; i >= 0; i-- {
		for j := i + 2; j < k; j++ {
			dp[i][j] = math.MaxInt
			for l := i + 1; l < j; l++ {
				dp[i][j] = min(dp[i][j], dp[i][l]+dp[l][j]+arr[j]-arr[i])
			}
		}
	}
	return dp[0][k-1]
}
