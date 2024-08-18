package bwc137

import (
	"math"
	"sort"
)

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

// 超时
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

// Store the largest 3 values for each row.
// Select any 3 rows and brute force all combinations.
func maximumValueSum3(board [][]int) int64 {
	m := len(board)
	matrix := make([][]int, m)
	for i := 0; i < m; i++ {
		matrix[i] = make([]int, 3)
	}
	for i := 0; i < m; i++ {
		sort.Slice(board[i], func(k, j int) bool {
			return board[i][k] > board[i][j]
		})
		for j := 0; j < 3; j++ {
			matrix[i][j] = board[i][j]
		}
	}
	var res int64
	res = math.MinInt64
	for x1 := 0; x1 < m; x1++ {
		for y1 := 0; y1 < 3; y1++ {
			for x2 := x1 + 1; x2 < m; x2++ {
				for y2 := 0; y2 < 3; y2++ {
					for x3 := x2 + 1; x3 < m; x3++ {
						for y3 := 0; y3 < 3; y3++ {
							if y1 != y2 && y1 != y3 && y2 != y3 {
								res = max(res, int64(matrix[x1][y1])+int64(matrix[x2][y2])+int64(matrix[x3][y3]))
							}
						}
					}
				}
			}
		}
	}
	return res
}
