package array

// 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。
//
//给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
//
//
//
//示例 1：
//
//
//输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
//输出：3
//解释：最大幻方尺寸为 3 。
//每一行，每一列以及两条对角线的和都等于 12 。
//- 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
//- 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
//- 对角线的和：5+4+3 = 6+4+2 = 12
//示例 2：
//
//
//输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
//输出：2
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 50
//1 <= grid[i][j] <= 106

// 枚举
func largestMagicSquare(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rowSum := make([][]int, m)
	colSum := make([][]int, n)
	for i := 0; i < m; i++ {
		rowSum[i] = make([]int, n+1)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			rowSum[i][j+1] = rowSum[i][j] + grid[i][j]
		}
	}
	for i := 0; i < n; i++ {
		colSum[i] = make([]int, m+1)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			colSum[i][j+1] = colSum[i][j] + grid[j][i]
		}
	}
	check := func(x, y, k int) bool {
		sum := rowSum[x][y+k] - rowSum[x][y]
		for i := x; i < x+k; i++ {
			c := rowSum[i][y+k] - rowSum[i][y]
			if c != sum {
				return false
			}
		}
		for i := y; i < y+k; i++ {
			c := colSum[i][x+k] - colSum[i][x]
			if c != sum {
				return false
			}
		}
		// 两条斜线处理
		c := 0
		for i := 0; i < k; i++ {
			c += grid[x+i][y+i]
		}
		if c != sum {
			return false
		}
		c = 0
		for i := 0; i < k; i++ {
			c += grid[x+k-1-i][y+i]
		}
		if c != sum {
			return false
		}
		return true
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 1; k <= min(m-i, n-j); k++ {
				if check(i, j, k) {
					res = max(res, k)
				}
			}
		}
	}
	return res
}
