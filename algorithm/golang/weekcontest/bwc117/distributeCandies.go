package bwc117

func distributeCandies(n int, limit int) int {
	var dfs func(k int, n int) int
	dfs = func(k int, n int) int {
		if k == 1 {
			if n <= limit {
				return 1
			} else {
				return 0
			}
		} else {
			res := 0
			for i := 0; i <= n && i <= limit; i++ {
				res += dfs(k-1, n-i)
			}
			return res
		}
	}
	return dfs(3, n)
}

// 优化解法
func distributeCandies2(n int, limit int) int64 {
	mem := make(map[int]int64)
	var dfs func(k int, n int) int64
	dfs = func(k int, n int) int64 {
		if k == 1 {
			if n <= limit {
				return 1
			} else {
				return 0
			}
		} else {
			if k == 2 {
				if v, ok := mem[n]; ok {
					return v
				}
			}
			var res int64
			for i := 0; i <= n && i <= limit; i++ {
				res += dfs(k-1, n-i)
			}
			if k == 2 {
				mem[n] = res
			}
			return res
		}
	}
	return dfs(3, n)
}

func distributeCandies3(n int, limit int) int64 {
	var res int64
	for i := 0; i <= n && i <= limit; i++ {
		left := n - i
		if left > 2*limit {
			res += 0
		} else if left <= limit {
			res += int64(left + 1)
		} else {
			// limit<n<=2*limit
			sub := left - limit
			// 能选择的范围是[sub,limit]
			res += int64(limit - sub + 1)
		}
	}
	return res
}
