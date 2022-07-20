package array

//给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
//
//每次「迁移」操作将会引发下述活动：
//
//位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
//位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
//位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
//请你返回 k 次迁移操作后最终得到的 二维网格。
//
//
//
//示例 1：
//
//
//
//输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
//输出：[[9,1,2],[3,4,5],[6,7,8]]
//示例 2：
//
//
//
//输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
//输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
//示例 3：
//
//输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
//输出：[[1,2,3],[4,5,6],[7,8,9]]
//
//
//提示：
//
//m == grid.length
//n == grid[i].length
//1 <= m <= 50
//1 <= n <= 50
//-1000 <= grid[i][j] <= 1000
//0 <= k <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shift-2d-grid
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//移动一次空间是可以优化的
func shiftGrid(grid [][]int, k int) [][]int {
	m := len(grid)
	n := len(grid[0])
	tmp := grid[m-1][n-1]
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if i == 0 && j == 0 {
				grid[i][j] = tmp
			} else if j-1 >= 0 {
				grid[i][j] = grid[i][j-1]
			} else {
				grid[i][j] = grid[i-1][n-1]
			}
		}
	}
	return grid
}

//正解
func shiftGrid2(grid [][]int, k int) [][]int {
	m := len(grid)
	n := len(grid[0])
	res := make([][]int, m)
	for i := 0; i < m; i++ {
		res[i] = make([]int, n)
	}
	if k >= m*n {
		k %= m * n
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			idx := (i*n + j + m*n - k) % (m * n)
			res[i][j] = grid[idx/n][idx%n]
		}
	}
	return res
}
