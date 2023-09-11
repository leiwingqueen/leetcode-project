package wc362

import "math"

func minimumMoves(grid [][]int) int {
	abs := func(a int) int {
		if a > 0 {
			return a
		} else {
			return -a
		}
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		if y == 3 {
			x++
			y = 0
		}
		if x == 3 {
			return 0
		}
		if grid[x][y] > 0 {
			return dfs(x, y+1)
		}
		res := math.MaxInt32
		for i := 0; i < 3; i++ {
			for j := 0; j < 3; j++ {
				if grid[i][j] > 1 {
					grid[i][j]--
					sub := dfs(x, y+1) + abs(x-i) + abs(y-j)
					if sub < res {
						res = sub
					}
					grid[i][j]++
				}
			}
		}
		return res
	}
	return dfs(0, 0)
}
