package wc347

import (
	"sort"
)

func maxIncreasingCells(mat [][]int) int {
	m, n := len(mat), len(mat[0])
	rows := make([][]int, m)
	for i := 0; i < m; i++ {
		rows[i] = make([]int, n)
		for j := 0; j < n; j++ {
			rows[i][j] = j
		}
		sort.Slice(rows[i], func(l, k int) bool {
			c1, c2 := rows[i][l], rows[i][k]
			return mat[i][c1] < mat[i][c2]
		})
	}
	cols := make([][]int, n)
	for i := 0; i < n; i++ {
		cols[i] = make([]int, m)
		for j := 0; j < m; j++ {
			cols[i][j] = j
		}
		sort.Slice(cols[i], func(l, k int) bool {
			r1, r2 := cols[i][l], cols[i][k]
			return mat[r1][i] < mat[r2][i]
		})
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
		// fmt.Println(fmt.Sprintf("[%d,%d]", x, y))
		if cache[x][y] >= 0 {
			return cache[x][y]
		}
		// 找到最小能移动的列的值，二分查找，这里要注意的一点是，如果存在多个值相等的列，那么我们需要都遍历一遍，然后取最大值
		sub1 := 0
		c := sort.Search(n, func(i int) bool {
			idx := rows[x][i]
			return mat[x][idx] > mat[x][y]
		})
		if c == n {
			sub1 = 1
		} else {
			i := c
			for i < n && mat[x][rows[x][i]] == mat[x][rows[x][c]] {
				s := dfs(x, rows[x][i]) + 1
				if s > sub1 {
					sub1 = s
				}
				i++
			}
		}
		sub2 := 0
		r := sort.Search(m, func(i int) bool {
			idx := cols[y][i]
			return mat[idx][y] > mat[x][y]
		})
		if r == m {
			sub2 = 1
		} else {
			i := r
			for i < m && mat[cols[y][i]][y] == mat[cols[y][r]][y] {
				s := dfs(cols[y][i], y) + 1
				if s > sub2 {
					sub2 = s
				}
				i++
			}
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
