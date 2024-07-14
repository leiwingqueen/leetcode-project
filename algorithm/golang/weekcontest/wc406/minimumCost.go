package wc406

import "math"

func minimumCost(m int, n int, horizontalCut []int, verticalCut []int) int {
	var dfs func(x1, y1, x2, y2 int) int
	dfs = func(x1, y1, x2, y2 int) int {
		if x2-x1 <= 1 && y2-y1 <= 1 {
			return 0
		}
		res := math.MaxInt
		if x2-x1 > 1 {
			for i := x1; i < x2; i++ {
				sub := dfs(x1, y1, i+1, y2) + dfs(i+1, y1, x2, y2) + horizontalCut[i]
				res = min(res, sub)
			}
		}
		if y2-y1 > 1 {
			for i := y1; i < y2; i++ {
				sub := dfs(x1, y1, x2, i+1) + dfs(x1, i+1, x2, y2) + verticalCut[i]
				res = min(res, sub)
			}
		}
		return res
	}
	return dfs(0, 0, m-1, n-1)
}
