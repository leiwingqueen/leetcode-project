package bfs

// 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
//
//两个相邻元素间的距离为 1 。
//
//
//
//示例 1：
//
//
//
//输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
//输出：[[0,0,0],[0,1,0],[0,0,0]]
//示例 2：
//
//
//
//输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
//输出：[[0,0,0],[0,1,0],[1,2,1]]
//
//
//提示：
//
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 104
//1 <= m * n <= 104
//mat[i][j] is either 0 or 1.
//mat 中至少有一个 0

// 多源DP
func updateMatrix(mat [][]int) [][]int {
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	m, n := len(mat), len(mat[0])
	var queue [][]int
	res := make([][]int, m)
	for i := 0; i < m; i++ {
		res[i] = make([]int, n)
		for j := 0; j < n; j++ {
			if mat[i][j] == 0 {
				queue = append(queue, []int{i, j})
			}
		}
	}
	depth := 1
	for len(queue) > 0 {
		size := len(queue)
		for i := 0; i < size; i++ {
			x, y := queue[i][0], queue[i][1]
			for _, dir := range dirs {
				nx, ny := x+dir[0], y+dir[1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && mat[nx][ny] > 0 && res[nx][ny] == 0 {
					res[nx][ny] = depth
					queue = append(queue, []int{nx, ny})
				}
			}
		}
		queue = queue[size:]
		depth++
	}
	return res
}
