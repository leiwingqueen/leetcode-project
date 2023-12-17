package wc376

func findMissingAndRepeatedValues(grid [][]int) []int {
	n := len(grid)
	cnt := make([]int, n*n+1)
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			cnt[grid[i][j]]++
		}
	}
	res := make([]int, 2)
	for i := 1; i <= n*n; i++ {
		if cnt[i] == 2 {
			res[0] = i
		} else if cnt[i] == 0 {
			res[1] = i
		}
	}
	return res
}
