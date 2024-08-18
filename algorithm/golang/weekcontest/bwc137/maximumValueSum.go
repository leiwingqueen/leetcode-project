package bwc137

import "math"

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

// 增加记忆
func maximumValueSum2(board [][]int) int64 {
	m, n := len(board), len(board[0])
	var dfs func(idx int) int64
	dfs = func(idx int) int64 {
		if idx == 3 {
			return 0
		}
		var res int64
		res = math.MinInt64
		for i := idx; i < m; i++ {
			for j := idx; j < n; j++ {
				// 选择[i,j]
				for l := 0; l < n; l++ {
					// i 行和idx行交换
					board[idx][l], board[i][l] = board[i][l], board[idx][l]
				}
				for l := 0; l < m; l++ {
					// j 列和idx列交换
					board[l][idx], board[l][j] = board[l][j], board[l][idx]
				}
				sub := dfs(idx+1) + int64(board[idx][idx])
				// 还原现场
				for l := 0; l < n; l++ {
					// i 行和idx行交换
					board[idx][l], board[i][l] = board[i][l], board[idx][l]
				}
				for l := 0; l < m; l++ {
					// j 列和idx列交换
					board[l][idx], board[l][j] = board[l][j], board[l][idx]
				}
				res = max(res, sub)
			}
		}
		return res
	}
	return dfs(0)
}
