package wc340

import "sort"

// 超时
func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if i == m-1 && j == n-1 {
				dp[i][j] = 1
			} else {
				dp[i][j] = -1
				if grid[i][j] > 0 {
					// 向右移动
					for k := 1; k <= grid[i][j] && j+k < n; k++ {
						if dp[i][j+k] > 0 && (dp[i][j] < 0 || dp[i][j+k]+1 < dp[i][j]) {
							dp[i][j] = dp[i][j+k] + 1
						}
					}
					// 向下移动
					for k := 1; k <= grid[i][j] && i+k < m; k++ {
						if dp[i+k][j] > 0 && (dp[i][j] < 0 || dp[i+k][j]+1 < dp[i][j]) {
							dp[i][j] = dp[i+k][j] + 1
						}
					}
				}
			}
		}
	}
	return dp[0][0]
}

// 单调栈优化
func minimumVisitedCells2(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	colSt := make([][]int, n)
	for i := m - 1; i >= 0; i-- {
		var st []int
		for j := n - 1; j >= 0; j-- {
			if i == m-1 && j == n-1 {
				dp[i][j] = 1
				st = append([]int{j}, st...)
				colSt[j] = append([]int{i}, colSt[j]...)
			} else {
				r := j + grid[i][j]
				if r >= n {
					r = n - 1
				}
				dp[i][j] = -1
				if len(st) > 0 {
					idx := sort.SearchInts(st, r+1)
					if idx > 0 {
						dp[i][j] = dp[i][st[idx-1]]
					}
				}
				r2 := i + grid[i][j]
				if r2 >= m {
					r2 = m - 1
				}
				st2 := colSt[j]
				if len(st2) > 0 {
					idx := sort.SearchInts(st2, r2+1)
					if idx > 0 && (dp[i][j] < 0 || dp[st2[idx-1]][j]+1 < dp[i][j]) {
						dp[i][j] = dp[st2[idx-1]][j] + 1
					}
				}
				if dp[i][j] >= 0 {
					for len(st) > 0 && dp[i][st[0]] <= dp[i][j] {
						st = st[1:]
					}
					st = append([]int{j}, st...)
					for len(st2) > 0 && dp[st2[0]][j] <= dp[i][j] {
						st2 = st2[1:]
					}
					st2 = append([]int{i}, st2...)
					colSt[j] = st2
				}
			}
		}
	}
	return dp[0][0]
}
