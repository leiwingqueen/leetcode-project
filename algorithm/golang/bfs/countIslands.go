package bfs

// 给你一个 m x n 的矩阵 grid 和一个正整数 k。一个 岛屿 是由 正 整数（表示陆地）组成的，并且陆地间 四周 连通（水平或垂直）。
//
//一个岛屿的总价值是该岛屿中所有单元格的值之和。
//
//返回总价值可以被 k 整除 的岛屿数量。
//
//
//
//示例 1:
//
//
//输入: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5
//
//输出: 2
//
//解释:
//
//网格中包含四个岛屿。蓝色高亮显示的岛屿的总价值可以被 5 整除，而红色高亮显示的岛屿则不能。
//
//示例 2:
//
//
//输入: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
//
//输出: 6
//
//解释:
//
//网格中包含六个岛屿，每个岛屿的总价值都可以被 3 整除。
//
//
//
//提示:
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 1000
//1 <= m * n <= 105
//0 <= grid[i][j] <= 106
//1 <= k < = 106

func countIslands(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	bfs := func(x, y int) int {
		queue := [][]int{{x, y}}
		sum := grid[x][y]
		grid[x][y] = 0
		for len(queue) > 0 {
			size := len(queue)
			for _, item := range queue[:size] {
				i, j := item[0], item[1]
				for _, dir := range dirs {
					nx, ny := i+dir[0], j+dir[1]
					if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0 {
						queue = append(queue, []int{nx, ny})
						sum += grid[nx][ny]
						grid[nx][ny] = 0
					}
				}
			}
			queue = queue[size:]
		}
		return sum
	}
	cnt := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] > 0 {
				sum := bfs(i, j)
				if sum%k == 0 {
					cnt++
				}
			}
		}
	}
	return cnt
}
