package wc367

// 给你一个下标从 0 开始、大小为 n * m 的二维整数矩阵 grid ，定义一个下标从 0 开始、大小为 n * m 的的二维矩阵 p。如果满足以下条件，则称 p 为 grid 的 乘积矩阵 ：
//
//对于每个元素 p[i][j] ，它的值等于除了 grid[i][j] 外所有元素的乘积。乘积对 12345 取余数。
//返回 grid 的乘积矩阵。
//
//
//
//示例 1：
//
//输入：grid = [[1,2],[3,4]]
//输出：[[24,12],[8,6]]
//解释：p[0][0] = grid[0][1] * grid[1][0] * grid[1][1] = 2 * 3 * 4 = 24
//p[0][1] = grid[0][0] * grid[1][0] * grid[1][1] = 1 * 3 * 4 = 12
//p[1][0] = grid[0][0] * grid[0][1] * grid[1][1] = 1 * 2 * 4 = 8
//p[1][1] = grid[0][0] * grid[0][1] * grid[1][0] = 1 * 2 * 3 = 6
//所以答案是 [[24,12],[8,6]] 。
//示例 2：
//
//输入：grid = [[12345],[2],[1]]
//输出：[[2],[0],[0]]
//解释：p[0][0] = grid[0][1] * grid[0][2] = 2 * 1 = 2
//p[0][1] = grid[0][0] * grid[0][2] = 12345 * 1 = 12345. 12345 % 12345 = 0 ，所以 p[0][1] = 0
//p[0][2] = grid[0][0] * grid[0][1] = 12345 * 2 = 24690. 24690 % 12345 = 0 ，所以 p[0][2] = 0
//所以答案是 [[2],[0],[0]] 。
//
//
//提示：
//
//1 <= n == grid.length <= 105
//1 <= m == grid[i].length <= 105
//2 <= n * m <= 105
//1 <= grid[i][j] <= 109

// 暴力解法，超时
func constructProductMatrix(grid [][]int) [][]int {
	mod := 12345
	n, m := len(grid), len(grid[0])
	res := make([][]int, n)
	for i := 0; i < n; i++ {
		res[i] = make([]int, m)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			p := 1
			for l := 0; l < n; l++ {
				for k := 0; k < m; k++ {
					if i != l || j != k {
						p = (p * grid[l][k]) % mod
					}
				}
			}
			res[i][j] = p
		}
	}
	return res
}

// 前缀和
func constructProductMatrix2(grid [][]int) [][]int {
	mod := 12345
	n, m := len(grid), len(grid[0])
	pre1, pre2 := make([]int, n*m+1), make([]int, n*m+1)
	pre1[0] = 1
	pre2[n*m] = 1
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			pre1[i*m+j+1] = (pre1[i*m+j] * grid[i][j]) % mod
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := m - 1; j >= 0; j-- {
			pre2[i*m+j] = (pre2[i*m+j+1] * grid[i][j]) % mod
		}
	}
	res := make([][]int, n)
	for i := 0; i < n; i++ {
		res[i] = make([]int, m)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			res[i][j] = (pre1[i*m+j] * pre2[i*m+j+1]) % mod
		}
	}
	return res
}
