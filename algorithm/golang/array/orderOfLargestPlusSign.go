package array

import (
	"leetcode-go/util"
)

// 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
//
//返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
//
//一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
//
//
//
//示例 1：
//
//
//
//输入: n = 5, mines = [[4, 2]]
//输出: 2
//解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
//示例 2：
//
//
//
//输入: n = 1, mines = [[0, 0]]
//输出: 0
//解释: 没有加号标志，返回 0 。
//
//
//提示：
//
//1 <= n <= 500
//1 <= mines.length <= 5000
//0 <= xi, yi < n
//每一对 (xi, yi) 都 不重复
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-plus-sign
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 解法1-暴力解法。时间复杂度O(n^3)
func orderOfLargestPlusSign(n int, mines [][]int) int {
	grid := make([][]int, n)
	for i := 0; i < n; i++ {
		grid[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			grid[i][j] = 1
		}
	}
	for _, d := range mines {
		grid[d[0]][d[1]] = 0
	}
	var cal func(x int, y int) int
	cal = func(x int, y int) int {
		if grid[x][y] == 0 {
			return 0
		}
		top := x
		for top >= 0 && grid[top][y] == 1 {
			top--
		}
		down := x
		for down < n && grid[down][y] == 1 {
			down++
		}
		left := y
		for left >= 0 && grid[x][left] == 1 {
			left--
		}
		right := y
		for right < n && grid[x][right] == 1 {
			right++
		}
		return util.Min(x-top, util.Min(down-x, util.Min(y-left, right-y)))
	}
	res := 0
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			c := cal(i, j)
			if c > res {
				res = c
			}
		}
	}
	return res
}
