package wc406

import "math"

func minimumCost(m int, n int, horizontalCut []int, verticalCut []int) int {
	cache := make(map[int]int)
	buildKey := func(x1, x2, y1, y2 int) int {
		return x1 | x2<<5 | y1<<10 | y2<<20
	}
	var dfs func(x1, y1, x2, y2 int) int
	dfs = func(x1, y1, x2, y2 int) int {
		if x2-x1 <= 1 && y2-y1 <= 1 {
			return 0
		}
		key := buildKey(x1, x2, y1, y2)
		if v, ok := cache[key]; ok {
			return v
		}
		res := math.MaxInt
		if x2-x1 > 1 {
			for i := x1; i < x2-1; i++ {
				sub := dfs(x1, y1, i+1, y2) + dfs(i+1, y1, x2, y2) + horizontalCut[i]
				res = min(res, sub)
			}
		}
		if y2-y1 > 1 {
			for i := y1; i < y2-1; i++ {
				sub := dfs(x1, y1, x2, i+1) + dfs(x1, i+1, x2, y2) + verticalCut[i]
				res = min(res, sub)
			}
		}
		cache[key] = res
		return res
	}
	return dfs(0, 0, m, n)
}
