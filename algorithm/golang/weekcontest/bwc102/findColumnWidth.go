package bwc102

func findColumnWidth(grid [][]int) []int {
	m, n := len(grid), len(grid[0])
	calLen := func(num int) int {
		if num == 0 {
			return 1
		}
		cnt := 0
		if num < 0 {
			cnt++
			num = -num
		}
		for num > 0 {
			num /= 10
			cnt++
		}
		return cnt
	}
	res := make([]int, n)
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			l := calLen(grid[j][i])
			if l > res[i] {
				res[i] = l
			}
		}
	}
	return res
}
