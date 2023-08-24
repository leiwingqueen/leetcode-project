package bfs

// 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
//
//如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
//
//请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
//
//
//
//示例 1：
//
//
//
//输入：grid = [[1,0],[0,1]]
//输出：0
//解释：没有一台服务器能与其他服务器进行通信。
//示例 2：
//
//
//
//输入：grid = [[1,0],[1,1]]
//输出：3
//解释：所有这些服务器都至少可以与一台别的服务器进行通信。
//示例 3：
//
//
//
//输入：grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
//输出：4
//解释：第一行的两台服务器互相通信，第三列的两台服务器互相通信，但右下角的服务器无法与其他服务器通信。
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m <= 250
//1 <= n <= 250
//grid[i][j] == 0 or 1

// 理解错误>_<
func countServers(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := [][]int{
		{0, -1},
		{0, 1},
		{-1, 0},
		{1, 0},
	}
	bfs := func(x, y int) int {
		queue := [][]int{
			{x, y},
		}
		grid[x][y] = 0
		cnt := 0
		for len(queue) > 0 {
			i, j := queue[0][0], queue[0][1]
			queue = queue[1:]
			cnt++
			for _, dir := range dirs {
				nx, ny := i+dir[0], j+dir[1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1 {
					grid[nx][ny] = 0
					queue = append(queue, []int{nx, ny})
				}
			}
		}
		return cnt
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				cnt := bfs(i, j)
				if cnt > 1 {
					res += cnt
				}
			}
		}
	}
	return res
}

// 逆向思维，总的节点数减去孤立的机器
// 孤立的机器我们只需要统计每一行或者每一列的节点数量即可
func countServers2(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rows, cols := make([]int, m), make([]int, n)
	total := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				rows[i]++
				cols[j]++
				total++
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 && rows[i] == 1 && cols[j] == 1 {
				total--
			}
		}
	}
	return total
}
