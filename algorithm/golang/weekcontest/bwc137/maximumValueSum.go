package bwc137

import "math"

// f(x,y,k)为前x行y列有k个车的最大值
// f(x,y,k)=max{f(x-1,y-1,k-1)+board[x-1][y-1],
func maximumValueSum(board [][]int) int64 {
	m, n := len(board), len(board[0])
	var dfs func(x, y, k int) int64
	dfs = func(x, y, k int) int64 {
		if k == 0 {
			return 0
		}
		var res int64
		res = math.MinInt64
		for i := x - 1; i >= k-1; i-- {
			for j := y - 1; j >= k-1; j-- {
				sub := dfs(i-1, y-1, k-1)
				res = max(res, sub+int64(board[i][j]))
			}
		}
		return res
	}
	return dfs(m, n, 3)
}
