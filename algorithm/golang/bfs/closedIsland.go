package bfs

// 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
//
//请返回 封闭岛屿 的数目。
//
//
//
//示例 1：
//
//
//
//输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
//输出：2
//解释：
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
//示例 2：
//
//
//
//输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//输出：1
//示例 3：
//
//输入：grid = [[1,1,1,1,1,1,1],
//             [1,0,0,0,0,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,1,0,1,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,0,0,0,0,1],
//             [1,1,1,1,1,1,1]]
//输出：2
//
//
//提示：
//
//1 <= grid.length, grid[0].length <= 100
//0 <= grid[i][j] <=1
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-closed-islands
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// dfs解法
func closedIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	visit := make([][]bool, m)
	for i := 0; i < m; i++ {
		visit[i] = make([]bool, n)
	}
	dirs := [][]int{
		{-1, 0},
		{1, 0},
		{0, -1},
		{0, 1},
	}
	var dfs func(x, y int) bool
	dfs = func(x, y int) bool {
		if grid[x][y] == 1 {
			return false
		}
		visit[x][y] = true
		flag := true
		if x == 0 || x == m-1 || y == 0 || y == n-1 {
			flag = false
		}
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0 && !visit[nx][ny] {
				visit[nx][ny] = true
				if !dfs(nx, ny) {
					flag = false
				}
			}
		}
		return flag
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 && !visit[i][j] {
				if dfs(i, j) {
					res++
				}
			}
		}
	}
	return res
}
