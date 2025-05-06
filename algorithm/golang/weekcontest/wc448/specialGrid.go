package wc448

import "math"

// 贪心
func specialGrid(n int) [][]int {
	size := int(math.Pow(2, float64(n)))
	matrix := make([][]int, size)
	for i := 0; i < size; i++ {
		matrix[i] = make([]int, size)
	}
	var dfs func(x, y int, size int, startNum int)
	dfs = func(x, y int, size int, startNum int) {
		if size == 1 {
			matrix[x][y] = startNum
			return
		}
		d := size / 2
		// 右上角
		dfs(x, y+d, d, startNum)
		// 右下角
		dfs(x+d, y+d, d, startNum+d*d)
		// 左下角
		dfs(x+d, y, d, startNum+2*d*d)
		// 左上角
		dfs(x, y, d, startNum+3*d*d)
	}
	dfs(0, 0, size, 0)
	return matrix
}
