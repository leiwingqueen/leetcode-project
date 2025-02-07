package array

// 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//
// 示例 1：
//
// 输入：n = 3
// 输出：[[1,2,3],[8,9,4],[7,6,5]]
// 示例 2：
//
// 输入：n = 1
// 输出：[[1]]
//
// 提示：
//
// 1 <= n <= 20

func generateMatrix(n int) [][]int {
	dirs := [][]int{
		{0, 1},
		{1, 0},
		{0, -1},
		{-1, 0},
	}
	matrix := make([][]int, n)
	for i := 0; i < n; i++ {
		matrix[i] = make([]int, n)
	}
	x, y, num := 0, 0, 1
	idx := 0
	for i := 0; i < n*n; i++ {
		matrix[x][y] = num
		// 移动到下一个位置
		nx, ny := x+dirs[idx][0], y+dirs[idx][1]
		if nx >= 0 && nx < n && ny >= 0 && ny < n {
			x = nx
			y = ny
		} else {
			idx = (idx + 1) % 4
			x, y = x+dirs[idx][0], y+dirs[idx][1]
		}
		num++
	}
	return matrix
}
