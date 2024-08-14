package bwc136

func minFlips(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	cnt1 := 0
	for i := 0; i < m; i++ {
		l, r := 0, n-1
		for l < r {
			if grid[i][l] != grid[i][r] {
				cnt1++
			}
			l++
			r--
		}
	}
	cnt2 := 0
	for i := 0; i < n; i++ {
		l, r := 0, m-1
		for l < r {
			if grid[l][i] != grid[r][i] {
				cnt2++
			}
			l++
			r--
		}
	}
	return min(cnt1, cnt2)
}
