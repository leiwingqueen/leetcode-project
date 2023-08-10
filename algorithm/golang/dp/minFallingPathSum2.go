package dp

import "math"

//f 给你一个 n x n 整数矩阵 grid ，请你返回 非零偏移下降路径 数字和的最小值。
//
//非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
//
//
//
//示例 1：
//
//
//
//输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
//输出：13
//解释：
//所有非零偏移下降路径包括：
//[1,5,9], [1,5,7], [1,6,7], [1,6,8],
//[2,4,8], [2,4,9], [2,6,7], [2,6,8],
//[3,4,8], [3,4,9], [3,5,7], [3,5,9]
//下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
//示例 2：
//
//输入：grid = [[7]]
//输出：7
//
//
//提示：
//
//n == grid.length == grid[i].length
//1 <= n <= 200
//-99 <= grid[i][j] <= 99

// 时间复杂度O(n^3)，居然过了？
func minFallingPathSum2(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// 初始化
	for i := 0; i < n; i++ {
		dp[m-1][i] = grid[m-1][i]
	}
	for i := m - 2; i >= 0; i-- {
		for j := 0; j < n; j++ {
			min := math.MaxInt
			for l := 0; l < n; l++ {
				if j != l && dp[i+1][l] < min {
					min = dp[i+1][l]
				}
			}
			dp[i][j] = min + grid[i][j]
		}
	}
	res := math.MaxInt
	for i := 0; i < n; i++ {
		if dp[0][i] < res {
			res = dp[0][i]
		}
	}
	return res
}
