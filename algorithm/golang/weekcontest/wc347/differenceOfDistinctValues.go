package wc347

func differenceOfDistinctValues(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	res := make([][]int, m)
	for i := 0; i < m; i++ {
		res[i] = make([]int, n)
	}
	cal := func(x, y int) int {
		i, j := x-1, y-1
		mp1 := make(map[int]struct{})
		for i >= 0 && j >= 0 {
			mp1[grid[i][j]] = struct{}{}
			i--
			j--
		}
		mp2 := make(map[int]struct{})
		i, j = x+1, y+1
		for i < m && j < n {
			mp2[grid[i][j]] = struct{}{}
			i++
			j++
		}
		if len(mp1) > len(mp2) {
			return len(mp1) - len(mp2)
		} else {
			return len(mp2) - len(mp1)
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			res[i][j] = cal(i, j)
		}
	}
	return res
}
