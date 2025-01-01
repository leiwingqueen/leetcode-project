package wc430

func minimumOperations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	res := 0
	for i := 0; i < n; i++ {
		expect := grid[0][i] + 1
		for j := 1; j < m; j++ {
			if grid[j][i] < expect {
				res += expect - grid[j][i]
			}
			expect = max(grid[j][i]+1, expect+1)
		}
	}
	return res
}
