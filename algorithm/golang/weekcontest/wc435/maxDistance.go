package wc435

// 先来个无脑暴力，超时
func maxDistance(s string, k int) int {
	n := len(s)
	dirs := [][]int{
		{0, 1},
		{0, -1},
		{-1, 0},
		{1, 0},
	}
	sFormat := make([]int, n)
	for i := 0; i < n; i++ {
		if s[i] == 'N' {
			sFormat[i] = 0
		} else if s[i] == 'S' {
			sFormat[i] = 1
		} else if s[i] == 'W' {
			sFormat[i] = 2
		} else {
			sFormat[i] = 3
		}
	}
	dis := func(x, y int) int {
		if x < 0 {
			x = -x
		}
		if y < 0 {
			y = -y
		}
		return x + y
	}
	var dfs func(x, y int, idx int, k int) int
	dfs = func(x, y int, idx int, k int) int {
		if idx == n {
			return dis(x, y)
		}
		// 不修改的情况
		dir := dirs[sFormat[idx]]
		res := max(dfs(x+dir[0], y+dir[1], idx+1, k), dis(x, y))
		// 修改的场景
		if k > 0 {
			for _, t := range dirs {
				res = max(dfs(x+t[0], y+t[1], idx+1, k-1), dis(x, y), res)
			}
		}
		return res
	}
	return dfs(0, 0, 0, k)
}
