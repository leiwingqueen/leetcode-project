package wc130

func satisfiesConditions(grid [][]int) bool {
	for i, row := range grid {
		for j, _ := range row {
			if i > 0 {
				// 跟上方比较
				if grid[i][j] != grid[i-1][j] {
					return false
				}
			}
			if j > 0 {
				// 跟左边比较
				if grid[i][j] == grid[i][j-1] {
					return false
				}
			}
		}
	}
	return true
}
