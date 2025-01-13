package wc432

func zigzagTraversal(grid [][]int) []int {
	m, n := len(grid), len(grid[0])
	x, y := 0, 0
	idx := 0
	var res []int
	for {
		if y == n {
			x++
			y--
		} else if y < 0 {
			x++
			y++
		}
		if x >= m {
			break
		}
		if idx%2 == 0 {
			res = append(res, grid[x][y])
		}
		if x%2 == 0 {
			y++
		} else {
			y--
		}
		idx++
	}
	return res
}
