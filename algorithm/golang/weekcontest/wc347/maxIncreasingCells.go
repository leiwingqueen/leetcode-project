package wc347

import "sort"

func maxIncreasingCells(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	rows := make([][]int, m)
	rowsIdx := make([][]int, m)
	for i := 0; i < m; i++ {
		rows[i] = make([]int, n)
		rowsIdx[i] = make([]int, n)
		for j := 0; j < n; j++ {
			rows[i][j] = j
		}
		sort.Slice(rows[i], func(l, k int) bool {
			c1, c2 := rows[i][l], rows[i][k]
			return mat[i][c1] < mat[i][c2]
		})
		for j := 0; j < n; j++ {
			k := rows[i][j]
			rowsIdx[i][k] = j
		}
	}
	cols := make([][]int, n)
	colsIds := make([][]int, n)
	for i := 0; i < n; i++ {
		cols[i] = make([]int, m)
		colsIds[i] = make([]int, m)
		for j := 0; j < m; j++ {
			cols[i][j] = j
		}
		sort.Slice(cols[i], func(l, k int) bool {
			r1, r2 := cols[i][l], cols[i][k]
			return mat[r1][i] < mat[r2][i]
		})
		for j := 0; j < m; j++ {
			k := cols[i][j]
			colsIds[i][k] = j
		}
	}
	cache := make([][]int, m)
	for i := 0; i < m; i++ {
		cache[i] = make([]int, n)
		for j := 0; j < n; j++ {
			cache[i][j] = -1
		}
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		if cache[x][y] >= 0 {
			return cache[x][y]
		}
		// 找到最小能移动的列的值
		sub1 := 0
		c := rowsIdx[x][y]
		if c == n-1 {
			sub1 = 1
		} else {
			sub1 = dfs(x, rows[x][c+1]) + 1
		}
		sub2 := 0
		r := colsIds[y][x]
		if r == m-1 {
			sub2 = 1
		} else {
			sub2 = dfs(y, cols[r+1][y]) + 1
		}
		if sub1 > sub2 {
			cache[x][y] = sub1
			return sub1
		} else {
			cache[x][y] = sub2
			return sub2
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			sub := dfs(i, j)
			if sub > res {
				res = sub
			}
		}
	}
	return res

}
