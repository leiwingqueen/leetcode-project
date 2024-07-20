package wc403

import "math"

// 给你一个二维 二进制 数组 grid。你需要找到 3 个 不重叠、面积 非零 、边在水平方向和竖直方向上的矩形，并且满足 grid 中所有的 1 都在这些矩形的内部。
//
// 返回这些矩形面积之和的 最小 可能值。
//
// 注意，这些矩形可以相接。
//
// 示例 1：
//
// 输入： grid = [[1,0,1],[1,1,1]]
//
// 输出： 5
//
// 解释：
//
// 位于 (0, 0) 和 (1, 0) 的 1 被一个面积为 2 的矩形覆盖。
// 位于 (0, 2) 和 (1, 2) 的 1 被一个面积为 2 的矩形覆盖。
// 位于 (1, 1) 的 1 被一个面积为 1 的矩形覆盖。
// 示例 2：
//
// 输入： grid = [[1,0,1,0],[0,1,0,1]]
//
// 输出： 5
//
// 解释：
//
// 位于 (0, 0) 和 (0, 2) 的 1 被一个面积为 3 的矩形覆盖。
// 位于 (1, 1) 的 1 被一个面积为 1 的矩形覆盖。
// 位于 (1, 3) 的 1 被一个面积为 1 的矩形覆盖。
//
// 提示：
//
// 1 <= grid.length, grid[i].length <= 30
// grid[i][j] 是 0 或 1。
// 输入保证 grid 中至少有三个 1 。

// f1(x1,y1,x2,y2)为下标从[x1,y1]到[x2,y2]使用1个三角形覆盖的最小面积
// f2(x1,y1,x2,y2)=
func minimumSum(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp1, dp2, dp3 := make([][][][]int, m), make([][][][]int, m), make([][][][]int, m)
	for i := 0; i < m; i++ {
		dp1[i] = make([][][]int, n)
		dp2[i] = make([][][]int, n)
		dp3[i] = make([][][]int, n)
		for j := 0; j < n; j++ {
			dp1[i][j] = make([][]int, m)
			dp2[i][j] = make([][]int, m)
			dp3[i][j] = make([][]int, m)
			for k := 0; k < m; k++ {
				dp1[i][j][k] = make([]int, n)
				dp2[i][j][k] = make([]int, n)
				dp3[i][j][k] = make([]int, n)
			}
		}
	}
	cal := func(x1, y1, x2, y2 int) int {
		l, r := math.MaxInt, 0
		d, u := math.MaxInt, 0
		for i := x1; i <= x2; i++ {
			for j := y1; j <= y2; j++ {
				if grid[i][j] == 1 {
					l = min(l, j)
					r = max(r, j)
					d = min(d, i)
					u = max(u, i)
				}
			}
		}
		return (r - l + 1) * (u - d + 1)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := i; k < m; k++ {
				for l := j; l < n; l++ {
					dp1[i][j][k][l] = cal(i, j, k, l)
				}
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := i; k < m; k++ {
				for l := j; l < n; l++ {
					dp2[i][j][k][l] = dp1[i][j][k][l]
					// 用一个横线分割
					for x := i; x < k; x++ {
						dp2[i][j][k][l] = min(dp2[i][j][k][l], dp1[i][j][x][l]+dp1[x+1][j][k][l])
					}
					// 用一个竖线分割
					for x := j; x < l; x++ {
						dp2[i][j][k][l] = min(dp2[i][j][k][l], dp1[i][j][k][x]+dp1[i][x+1][k][l])
					}
				}
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := i; k < m; k++ {
				for l := j; l < n; l++ {
					dp3[i][j][k][l] = dp2[i][j][k][l]
					// 用一个横线分割
					for x := i; x < k; x++ {
						dp3[i][j][k][l] = min(dp3[i][j][k][l], dp1[i][j][x][l]+dp2[x+1][j][k][l])
					}
					// 用一个竖线分割
					for x := j; x < l; x++ {
						dp2[i][j][k][l] = min(dp3[i][j][k][l], dp1[i][j][k][x]+dp2[i][x+1][k][l])
					}
				}
			}
		}
	}
	return dp3[0][0][m-1][n-1]
}
