package wc345

// 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
//
//你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
//
//从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
//返回你在矩阵中能够 移动 的 最大 次数。
//
//
//
//示例 1：
//
//
//输入：grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
//输出：3
//解释：可以从单元格 (0, 0) 开始并且按下面的路径移动：
//- (0, 0) -> (0, 1).
//- (0, 1) -> (1, 2).
//- (1, 2) -> (2, 3).
//可以证明这是能够移动的最大次数。
//示例 2：
//
//
//输入：grid = [[3,2,4],[2,1,9],[1,1,7]]
//输出：0
//解释：从第一列的任一单元格开始都无法移动。
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//2 <= m, n <= 1000
//4 <= m * n <= 105
//1 <= grid[i][j] <= 106

func maxMoves(grid [][]int) int {
	dirs := [][]int{
		{-1, 1},
		{0, 1},
		{1, 1},
	}
	m, n := len(grid), len(grid[0])
	cache := make([][]int, m)
	for i := 0; i < m; i++ {
		cache[i] = make([]int, n)
		for j := 0; j < n; j++ {
			cache[i][j] = -1
		}
	}
	var dfs func(x, y int) int
	dfs = func(x, y int) int {
		if cache[x][y] >= 0 {
			return cache[x][y]
		}
		res := 0
		for _, dir := range dirs {
			nx, ny := x+dir[0], y+dir[1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > grid[x][y] {
				sub := dfs(nx, ny) + 1
				if sub > res {
					res = sub
				}
			}
		}
		cache[x][y] = res
		return res
	}
	res := 0
	for i := 0; i < m; i++ {
		r := dfs(i, 0)
		if r > res {
			res = r
		}
	}
	return res
}

// dp
func maxMoves2(grid [][]int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	dirs := [][]int{
		{-1, 1},
		{0, 1},
		{1, 1},
	}
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// dp迭代
	for i := n - 2; i >= 0; i-- {
		for j := 0; j < m; j++ {
			for _, dir := range dirs {
				x, y := j+dir[0], i+dir[1]
				if x >= 0 && x < m && grid[j][i] < grid[x][y] {
					dp[j][i] = max(dp[j][i], dp[x][y]+1)
				}
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		res = max(res, dp[i][0])
	}
	return res
}

// 空间优化
func maxMoves3(grid [][]int) int {
	max := func(a, b int) int {
		if a > b {
			return a
		} else {
			return b
		}
	}
	dirs := [][]int{
		{-1, 1},
		{0, 1},
		{1, 1},
	}
	m, n := len(grid), len(grid[0])
	pre := make([]int, m)
	cur := make([]int, m)
	// dp迭代
	for j := n - 2; j >= 0; j-- {
		for i := 0; i < m; i++ {
			cur[i] = 0
			for _, dir := range dirs {
				x, y := i+dir[0], j+dir[1]
				if x >= 0 && x < m && grid[i][j] < grid[x][y] {
					cur[i] = max(cur[i], pre[x]+1)
				}
			}
		}
		// fmt.Printf("%v\n", cur)
		copy(pre, cur)
	}
	res := 0
	for i := 0; i < m; i++ {
		res = max(res, pre[i])
	}
	return res
}
