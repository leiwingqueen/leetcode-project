package bwc146

// 给你一个大小为 m x n 的二维整数数组 grid 和一个整数 k 。
//
//你的任务是统计满足以下 条件 且从左上格子 (0, 0) 出发到达右下格子 (m - 1, n - 1) 的路径数目：
//
//每一步你可以向右或者向下走，也就是如果格子存在的话，可以从格子 (i, j) 走到格子 (i, j + 1) 或者格子 (i + 1, j) 。
//路径上经过的所有数字 XOR 异或值必须 等于 k 。
//请你返回满足上述条件的路径总数。
//
//由于答案可能很大，请你将答案对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//输入：grid = [[2, 1, 5], [7, 10, 0], [12, 6, 4]], k = 11
//
//输出：3
//
//解释：
//
//3 条路径分别为：
//
//(0, 0) → (1, 0) → (2, 0) → (2, 1) → (2, 2)
//(0, 0) → (1, 0) → (1, 1) → (1, 2) → (2, 2)
//(0, 0) → (0, 1) → (1, 1) → (2, 1) → (2, 2)
//示例 2：
//
//输入：grid = [[1, 3, 3, 3], [0, 3, 3, 2], [3, 0, 1, 1]], k = 2
//
//输出：5
//
//解释：
//
//5 条路径分别为：
//
//(0, 0) → (1, 0) → (2, 0) → (2, 1) → (2, 2) → (2, 3)
//(0, 0) → (1, 0) → (1, 1) → (2, 1) → (2, 2) → (2, 3)
//(0, 0) → (1, 0) → (1, 1) → (1, 2) → (1, 3) → (2, 3)
//(0, 0) → (0, 1) → (1, 1) → (1, 2) → (2, 2) → (2, 3)
//(0, 0) → (0, 1) → (0, 2) → (1, 2) → (2, 2) → (2, 3)
//示例 3：
//
//输入：grid = [[1, 1, 1, 2], [3, 0, 3, 2], [3, 0, 2, 2]], k = 10
//
//输出：0
//
//
//
//提示：
//
//1 <= m == grid.length <= 300
//1 <= n == grid[r].length <= 300
//0 <= grid[r][c] < 16
//0 <= k < 16

func countPathsWithXorValue(grid [][]int, k int) int {
	mod := 1_000_000_007
	maxVal := 16
	m, n := len(grid), len(grid[0])
	dp := make([][][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			dp[i][j] = make([]int, maxVal)
		}
	}
	// dp初始化
	dp[0][0][grid[0][0]] = 1
	xor := grid[0][0]
	for i := 1; i < n; i++ {
		xor ^= grid[0][i]
		dp[0][i][xor] = 1
	}
	xor = grid[0][0]
	for i := 1; i < m; i++ {
		xor ^= grid[i][0]
		dp[i][0][xor] = 1
	}
	// dp迭代
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			for l := 0; l < maxVal; l++ {
				dp[i][j][l] = (dp[i-1][j][l^grid[i][j]] + dp[i][j-1][l^grid[i][j]]) % mod
			}
		}
	}
	return dp[m-1][n-1][k]
}