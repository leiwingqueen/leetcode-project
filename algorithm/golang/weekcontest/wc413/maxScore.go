package wc413

// 给你一个由正整数构成的二维矩阵 grid。
//
//你需要从矩阵中选择 一个或多个 单元格，选中的单元格应满足以下条件：
//
//所选单元格中的任意两个单元格都不会处于矩阵的 同一行。
//所选单元格的值 互不相同。
//你的得分为所选单元格值的总和。
//
//返回你能获得的 最大 得分。
//
//
//
//示例 1：
//
//输入： grid = [[1,2,3],[4,3,2],[1,1,1]]
//
//输出： 8
//
//解释：
//
//
//
//选择上图中用彩色标记的单元格，对应的值分别为 1、3 和 4 。
//
//示例 2：
//
//输入： grid = [[8,7,6],[8,3,2]]
//
//输出： 15
//
//解释：
//
//
//
//选择上图中用彩色标记的单元格，对应的值分别为 7 和 8 。
//
//
//
//提示：
//
//1 <= grid.length, grid[i].length <= 10
//1 <= grid[i][j] <= 100

// 简单回溯
func maxScore(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var dfs func(choose []bool, idx int) int
	dfs = func(choose []bool, idx int) int {
		if idx == m {
			return 0
		}
		res := 0
		for i := 0; i < n; i++ {
			num := grid[idx][i]
			if choose[num] {
				continue
			}
			choose[num] = true
			sum := dfs(choose, idx+1) + num
			choose[num] = false
			res = max(res, sum)
		}
		return res
	}
	return dfs(make([]bool, 101), 0)
}
